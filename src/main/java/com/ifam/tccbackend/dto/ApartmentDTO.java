package com.ifam.tccbackend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.model.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ApartmentDTO(String number, Block block, List<Resident> residents) {
        this.number = number;
        this.block = block;
        this.residents = residents;
    }

    public Apartment getModel() {
        return new Apartment(number, block);
    }

    public ApartmentDTO getDto(Apartment apartment){
        ApartmentDTO dto = new ApartmentDTO();
        dto.setId(apartment.getId());
        dto.setNumber(apartment.getNumber());
        dto.setBlock(apartment.getBlock());
        dto.setResidents(apartment.getResidents());
        return dto;
    }


}