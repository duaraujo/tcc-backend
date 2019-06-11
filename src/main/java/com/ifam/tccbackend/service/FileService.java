package com.ifam.tccbackend.service;

import com.ifam.tccbackend.dto.FileDTO;
import com.ifam.tccbackend.model.EMediaType;
import com.ifam.tccbackend.model.FileData;
import com.ifam.tccbackend.repository.IFile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Service
public class FileService {
    private	final 	String 				UPLOAD_DIR		= "/uploads/";
    private			IFile				repository;
    private         HttpServletRequest  request;
    private 		Environment 		env;

    @Autowired
    public FileService(IFile repository, HttpServletRequest request, Environment env) {
        this.repository		= repository;
        this.request		= request;
        this.env = env;
    }

    public FileDTO writeFile(String name, EMediaType type, byte[] data) {
        try {
            String extension        = FilenameUtils.getExtension(name);
            File fileLocation       = getNewFile(extension);
            FileOutputStream fos    = new FileOutputStream(fileLocation);
            FileData metadata       = new FileData();

            validateFile(name, type);

            fos.write(data);
            fos.close();

            metadata.setPath(Paths.get(fileLocation.getName()).toString());
            metadata.setExtension(extension);
            metadata.setName(name);
            metadata.setType(type);

            return FileDTO.toDTO(repository.save(metadata));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    private File getNewFile(String ext) throws IOException {
        if(ext == null || ext.trim().isEmpty()) {
            ext = ".dat";
        }

        String	name	= String.format("%s.%s", java.util.UUID.randomUUID(), ext);
        File	dir		= new File(getUploadDir());
        File	file	= new File(dir, name);

        if (!dir.mkdirs() && !file.createNewFile())
            System.out.println("file.internal.error");

        return file;
    }

    private void validateFile(String name, EMediaType type) {
        switch (type) {
            case PICTURE2D:
                break;

            case PICTURE360:
                if(!name.matches("(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP)$"))
                    System.out.println("format file not supported");
                break;

            case VIDEO2D:
                break;

            case VIDEO360:
                if(!name.matches("(.*/)*.+\\.(mp4|MP4)$"))
                    System.out.println("format file not supported");
                break;
        }
    }

    public String getPhisicalPath() {
        return this.env.getProperty("uploads.folder");
    }

    private String getUploadDir() {
		//String	uploadPath	=  request.getServletContext().getRealPath(UPLOAD_DIR);
        String uploadPath = new File(getPhisicalPath(), UPLOAD_DIR).getAbsolutePath();

        if(!new File(uploadPath).exists())
            if (!new File(uploadPath).mkdir())
                System.out.println("File interno erro");

        return uploadPath;
    }

    public FileDTO findOne(long fileId) {
        return FileDTO.toDTO(repository.findById(fileId));
    }

    public File getFile(FileDTO file) {
        return new File(getUploadDir(), file.getPath());
    }
}