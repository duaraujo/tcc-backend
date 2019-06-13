package com.ifam.tccbackend.repository;

import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResident extends JpaRepository<Resident, Long> {

    List<Resident> getResidentsByApartment(Apartment id);


}

