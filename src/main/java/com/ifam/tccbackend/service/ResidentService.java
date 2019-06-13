package com.ifam.tccbackend.service;

import com.ifam.tccbackend.dto.ResidentDTO;
import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.Resident;
import com.ifam.tccbackend.repository.IApartment;
import com.ifam.tccbackend.repository.IResident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {

    private final IResident iResident;
    private final IApartment iApartment;

    @Autowired
    public ResidentService(IResident iResident, IApartment iApartment){
        this.iResident = iResident;
        this.iApartment = iApartment;
    }

    public List<Resident> findAll(){ return iResident.findAll(); }

    public Resident findOne(Long id){ return iResident.getOne(id); }

    public Resident save(ResidentDTO residentDTO){ return iResident.save(residentDTO.getModel()); }

    public Resident save(Resident resident){ return iResident.save(resident); }

    public void delete(Long id){ iResident.deleteById(id);}

    public List<Resident> listResidentByApartment(Long apartmentId){
        Apartment a = iApartment.getOne(apartmentId);
        return iResident.getResidentsByApartment(a);
    }

}