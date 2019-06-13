package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockDTO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int floor;

    @Getter
    @Setter
    private Double rating;

    @Getter
    @Setter
    private String height;

    @Getter
    @Setter
    private FileModel sendImage;

    public BlockDTO(String name, int floor, Double rating, String height, FileModel sendImage) {
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.sendImage = sendImage;
    }

    public Block getModel() {
        return new Block(name, floor, rating, height, sendImage);
    }

    public BlockDTO getDto(Block block) {
        return new BlockDTO(block.getName(), block.getFloor(),
                block.getRating(), block.getHeight(),
                block.getSendImage());
    }

}