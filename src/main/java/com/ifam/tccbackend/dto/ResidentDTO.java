package com.ifam.tccbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.FileModel;
import com.ifam.tccbackend.model.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidentDTO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private Date dataNasc;

    @Getter
    @Setter
    private Apartment apartment;

    @Getter
    @Setter
    private List<FileModel> gallery;

    public ResidentDTO(String name, String email, String gender, String phone, Date dataNasc, Apartment apartment, List<FileModel> gallery) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.dataNasc = dataNasc;
        this.apartment = apartment;
        this.gallery = gallery;
    }

    public Resident getModel() {
        return new Resident(name, email, gender, phone, dataNasc, apartment, gallery);
    }

    public ResidentDTO getDto(Resident resident) {
        return new ResidentDTO(resident.getName(), resident.getEmail(),
                resident.getGender(), resident.getPhone(),
                resident.getDataNasc(), resident.getApartment(), resident.getGallery());
    }

}