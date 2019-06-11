package com.ifam.tccbackend.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifam.tccbackend.dto.FileDTO;
import com.ifam.tccbackend.model.EMediaType;
import com.ifam.tccbackend.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/rest/file")
@RestController
public class FileREST {
    private final FileService service;

    @Autowired
    public FileREST(FileService service) {
        this.service = service;
    }

    @RequestMapping(value="/{type}", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @PathVariable("type") String type){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                return new ResponseEntity<>(service.writeFile(file.getOriginalFilename(), EMediaType.parse(type), bytes), HttpStatus.OK);
            } catch (Exception e) {
                ResponseEntity.badRequest().body("file.internal.error");
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

   // @CacheControl(maxAge= 003600 * 24 * 7 * 365, policy = { CachePolicy.PUBLIC })
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public void download(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileDTO metadata	    = service.findOne(id);
        File		file		= service.getFile(metadata);

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.addHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));
        response.addHeader("Accept-Ranges", "bytes");
        response.setContentType("image/" + metadata.getExtension());
        //response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        IOUtils.copyLarge(new FileInputStream(file), response.getOutputStream());

    }
}
