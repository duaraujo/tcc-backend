package com.ifam.tccbackend.service;

import com.ifam.tccbackend.PropertiesFile.FileStorageProperties;
import com.ifam.tccbackend.controller.FileModelREST;
import com.ifam.tccbackend.dto.FileModelDTO;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.model.FileStorageException;
import com.ifam.tccbackend.model.MyFileNotFoundException;
import com.ifam.tccbackend.repository.IFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileService {

    private Path fileStorageLocation;
    private IFileModel repository;
    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties, IFileModel repository) {

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        System.out.println("==> File Storage Location: "+ fileStorageLocation);

        this.repository = repository;


        try {
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("Tentar criar diretorio");
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public FileModelDTO writeFile(FileModel fileModel) {
        return FileModelDTO.toDTO(repository.save(fileModel));
    }

    public String hashName(String ext) {
        if(ext == null || ext.trim().isEmpty())
            ext = ".dat";
        String	name	=   String.format("%s_%s", java.util.UUID.randomUUID(), ext);
        return name;
    }

    public String storeFile(MultipartFile file, String name) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            if(!fileName.contains(".")){
                fileName = fileName + ".png";
            }

            //----------------------------------------------------------------------------------------

            String nameDirectory =name.substring(0, name.lastIndexOf(' ') == -1 ?  name.length() : name.lastIndexOf(' '));

            Path path = Paths.get( fileStorageProperties.getUploadDir()+"/"+nameDirectory).toAbsolutePath().normalize();

            Files.createDirectories(path);

            // Copy file to the target location (Replacing existing file with the same name)
            String hashName = hashName(fileName);
            Path targetLocation = this.fileStorageLocation.resolve(nameDirectory+"/"+hashName);

            //---------------------------------------------------------------------------------------

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return hashName;
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    public Resource loadFileAsResource(String fileName, String path) {
        try {
            Path filePath = this.fileStorageLocation.resolve(path+"/"+fileName).normalize();
            System.out.println(filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }


    public FileModelDTO findOne(long fileId) {
        FileModel model = this.repository.getOne(fileId);
        return FileModelDTO.toDTO(model);
    }



}