package com.ifam.tccbackend.controller;

import com.ifam.tccbackend.dto.FileModelDTO;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/file")
public class FileModelREST {

    private static final Logger logger = LoggerFactory.getLogger(FileModelREST.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public FileModelDTO upload(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .path(fileName)
                .toUriString();
        FileModel fileModel = new FileModel(fileName, fileDownloadUri, file.getContentType(), file.getSize());

        return fileStorageService.writeFile(fileModel);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<FileModelDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> upload(file))
                .collect(Collectors.toList());
    }


    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) {

        String fileName = fileStorageService.findOne(id).getName();
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



}