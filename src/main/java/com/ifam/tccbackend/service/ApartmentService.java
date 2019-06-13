package com.ifam.tccbackend.service;


import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.repository.IApartment;
import com.ifam.tccbackend.repository.IBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    private IApartment iApartment;
    private IBlock iBlock;

    @Autowired
    public ApartmentService (IApartment iApartment, IBlock iBlock){
        this.iApartment = iApartment;
        this.iBlock = iBlock;
    }

    public List<Apartment> listApartamentByBlock(Long blockId){
        Block b = iBlock.getOne(blockId);
        return iApartment.getApartmentsByBlock(b);
    }

    public List<Apartment> findAll(){ return iApartment.findAll(); }

    public Apartment findOne(Long id){ return this.iApartment.getOne(id); }

    public Apartment save(Apartment apartment){ return iApartment.save(apartment); }

    public void delete(Long id){ iBlock.deleteById(id);}

}