package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class BlockDTO {

    @Getter
    @Setter
    @JsonProperty("text")
    private 	String							text;

    @Getter
    @Setter
    @JsonProperty("sendImage")
    private 	FileModelDTO							sendImage;

    public BlockDTO() {
        super();
    }


}