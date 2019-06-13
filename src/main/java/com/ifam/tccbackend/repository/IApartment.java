package com.ifam.tccbackend.repository;

import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IApartment extends JpaRepository<Apartment, Long> {

    List<Apartment> getApartmentsByBlock(Block b);


}
