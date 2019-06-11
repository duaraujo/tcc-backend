package com.ifam.tccbackend.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ifam.tccbackend.model.FileData;
import lombok.Getter;
import lombok.Setter;

public class FileDTO {
    @Getter
    @Setter
    @JsonProperty(value="id")
    private		long		id;

    @Getter
    @Setter
    @JsonProperty(value="path")
    private		String		path;

    @Getter
    @Setter
    @JsonProperty(value="name")
    private		String		name;

    @Getter
    @Setter
    @JsonProperty(value="extension")
    private		String		extension;

    @Getter
    @Setter
    @JsonProperty(value="type")
    private		String		type;

    public FileDTO() {
        super();
    }

    public FileDTO(FileData entity) {
        id			= entity.getId();
        path		= entity.getPath();
        name		= entity.getName();
        extension	= entity.getExtension();
    }

    @JsonIgnore
    public FileData getModel() {
        FileData model = new FileData();
        model.setId(id);
        model.setName(name);
        model.setExtension(extension);
        model.setPath(path);
        return model;
    }

    public static FileDTO toDTO(FileData entity) {
        if (entity != null)
            return new FileDTO(entity);
        return null;
    }

}