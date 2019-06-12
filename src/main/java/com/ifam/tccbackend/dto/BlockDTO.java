package com.ifam.tccbackend.dto;

import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.util.List;
@NoArgsConstructor
public class BlockDTO {

    @Getter
    @Setter
    private 	String						name;

    @Getter
    @Setter
    private 	int						    floor;

    @Getter
    @Setter
    private 	Double						rating;

    @Getter
    @Setter
    private 	String						height;

    @Getter
    @Setter
    private     List<Apartment>             apartments;

    @Getter
    @Setter
    @OneToOne
    private     FileModel                    sendImage;

    public BlockDTO(String name, int floor, Double rating, String height, List<Apartment> apartments, FileModel sendImage) {
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.apartments = apartments;
        this.sendImage = sendImage;
    }

    public Block DtoToModel(){
        return new Block(name, floor, rating, height, sendImage);
    }

}