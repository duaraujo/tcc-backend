package com.ifam.tccbackend.service;

import com.ifam.tccbackend.PropertiesFile.FileStorageProperties;
import com.ifam.tccbackend.dto.FileDTO;
import com.ifam.tccbackend.dto.FileModelDTO;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.model.FileStorageException;
import com.ifam.tccbackend.model.MyFileNotFoundException;
import com.ifam.tccbackend.repository.IFile;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private IFileModel repository;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties,
                              IFileModel repository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.repository = repository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public FileModelDTO writeFile(FileModel fileModel){
        return FileModelDTO.toDTO(repository.save(fileModel));
    }

    public String hashName(String ext) {
        if(ext == null || ext.trim().isEmpty())
            ext = ".dat";
        String	name	=   String.format("%s_%s", java.util.UUID.randomUUID(), ext);
        return name;
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            String hashName = hashName(fileName);
            Path targetLocation = this.fileStorageLocation.resolve(hashName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return hashName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
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