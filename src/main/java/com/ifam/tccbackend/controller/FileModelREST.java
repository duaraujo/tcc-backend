package com.ifam.tccbackend.controller;

import com.ifam.tccbackend.dto.FileModelDTO;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.service.FileService;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/file")
public class FileModelREST {

    private static final Logger logger = LoggerFactory.getLogger(FileModelREST.class);
    //public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @Autowired
    private FileService fileService;

    @PostMapping(path = "/{name}")
    public FileModelDTO upload(@RequestParam("file") MultipartFile file, @PathVariable("name") String name) {
        String fileName = fileService.storeFile(file, name);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .path(fileName)
                .toUriString();
        FileModel fileModel = new FileModel(fileName, fileDownloadUri, file.getContentType());

        return fileService.writeFile(fileModel);
    }

    @PostMapping("/uploadMultipleFiles/{name}")
    public List<FileModelDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("name") String name) {

        return Arrays.asList(files)
                .stream()
                .map(file -> upload(file, name))
                .collect(Collectors.toList());
    }


    @RequestMapping(value = "/{id}/{name}", method = {RequestMethod.GET})
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id,@PathVariable String name, HttpServletRequest request) {

        String fileName = fileService.findOne(id).getName();
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName, name.substring(0, name.lastIndexOf(' ') == -1 ?  name.length() : name.lastIndexOf(' ')));
        System.out.println("=== "+resource.getFilename());

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