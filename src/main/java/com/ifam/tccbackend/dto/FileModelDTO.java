package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifam.tccbackend.model.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileModelDTO {

    @Getter
    @Setter
    @JsonProperty(value = "id")
    private long id;
    @Getter
    @Setter
    @JsonProperty(value = "name")
    private String name;
    @Getter
    @Setter
    @JsonProperty(value = "path")
    private String path;
    @Getter
    @Setter
    @JsonProperty(value = "type")
    private String type;

    public FileModelDTO(FileModel entity) {
        id = entity.getId();
        path = entity.getPath();
        name = entity.getName();
        type = entity.getType();
    }

    public FileModel modelToDto() {
        FileModel model = new FileModel();
        model.setId(this.id);
        model.setName(this.name);
        model.setType(this.type);
        model.setPath(this.path);
        return model;
    }

    public static FileModelDTO toDTO(FileModel entity) {
        if (entity != null)
            return new FileModelDTO(entity);
        return null;
    }

}