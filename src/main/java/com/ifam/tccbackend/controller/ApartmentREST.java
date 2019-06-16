package com.ifam.tccbackend.controller;

import com.ifam.tccbackend.dto.ApartmentDTO;
import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.service.ApartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/rest/apartments")
public class ApartmentREST {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public List<ApartmentDTO> findAllDTO() {
        return apartmentService.findAllDTO();
    }

    //@GetMapping
    public List<Apartment> findAllModel() {
        return apartmentService.findAll();
    }

    @PostMapping
    public void save(@RequestBody Apartment apartment) {
        apartmentService.save(apartment);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Apartment> findOne(@PathVariable Long id) {
        Apartment b = apartmentService.findOne(id);
        return b != null ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        apartmentService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Apartment> update(@PathVariable Long id, @RequestBody Apartment apartment) {
        Apartment apartmentSalvo = apartmentService.findOne(id);
        if (apartmentSalvo == null)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(apartment, apartmentSalvo, "id");
        apartmentService.save(apartmentSalvo);
        return ResponseEntity.ok(apartmentSalvo);
    }

    /*@RequestMapping(method = RequestMethod.GET, path = "/block/{block}")
    public List<Apartment> listApartamentsByBlock(@PathVariable Long block) {
        return apartmentService.listApartamentByBlock(block);
    }*/

    @RequestMapping(method = RequestMethod.GET, path = "/block/{block}")
    public List<ApartmentDTO> listApartamentsByBlock(@PathVariable Long block) {
        List<Apartment> apartments = apartmentService.listApartamentByBlock(block);
        List<ApartmentDTO> dtos = new ArrayList<>();
        for(Apartment ap: apartments){
            dtos.add(new ApartmentDTO().getDto(ap));
        }
        return dtos;
    }

}