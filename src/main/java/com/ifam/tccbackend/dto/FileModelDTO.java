package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifam.tccbackend.model.FileModel;
import lombok.Getter;
import lombok.Setter;

public class FileModelDTO {

    @Getter
    @Setter
    @JsonProperty(value="id")
    private		long		id;
    @Getter
    @Setter
    @JsonProperty(value="name")
    private String name;
    @Getter
    @Setter
    @JsonProperty(value="path")
    private String path;
    @Getter
    @Setter
    @JsonProperty(value="type")
    private String type;
    @Getter
    @Setter
    @JsonProperty(value="size")
    private long size;


    public FileModelDTO() {
        super();
    }

    public FileModelDTO(FileModel entity) {
        id			= entity.getId();
        path		= entity.getPath();
        name		= entity.getName();
        type	    = entity.getType();
        size        = entity.getSize();
    }

    @JsonIgnore
    public FileModelDTO getModel() {
        FileModelDTO model = new FileModelDTO();
        model.setId(id);
        model.setName(name);
        model.setType(type);
        model.setPath(path);
        return model;
    }

    public static FileModelDTO toDTO(FileModel entity) {
        if (entity != null)
            return new FileModelDTO(entity);
        return null;
    }

}