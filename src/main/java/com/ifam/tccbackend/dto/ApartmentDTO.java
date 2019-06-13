package com.ifam.tccbackend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.model.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApartmentDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private Block block;

    @Getter
    @Setter
    private List<Resident> residents;

    @Getter
    @Setter
    @OneToOne
    private FileModel sendImage;

    public ApartmentDTO(long id, String number, Block block, List<Resident> residents, FileModel sendImage) {
        this.id = id;
        this.number = number;
        this.block = block;
        this.residents = residents;
        this.sendImage = sendImage;
    }



}
