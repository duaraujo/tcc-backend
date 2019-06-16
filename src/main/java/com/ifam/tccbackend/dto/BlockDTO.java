package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifam.tccbackend.model.Apartment;
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
    private Long id;

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
    private List<Apartment> apartments;

    public BlockDTO(Long id, String name, int floor, Double rating, String height, List<Apartment> apartments) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.apartments = apartments;
    }

    public Block getModel() {
        return new Block(name, floor, rating, height, apartments);
    }

    public BlockDTO getDto(Block block) {
        return new BlockDTO(
                block.getId(),
                block.getName(),
                block.getFloor(),
                block.getRating(),
                block.getHeight(),
                block.getApartments());
    }


}