package com.ifam.tccbackend.controller;

import com.ifam.tccbackend.dto.ResidentDTO;
import com.ifam.tccbackend.kafka.OrderConsumer;
import com.ifam.tccbackend.kafka.OrderProducer;
import com.ifam.tccbackend.model.Apartment;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.model.Resident;
import com.ifam.tccbackend.service.ApartmentService;
import com.ifam.tccbackend.service.ResidentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/rest/residents")
public class ResidentREST {

    @Autowired
    private ResidentService residentService;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private OrderProducer producer;
    @Autowired
    private OrderConsumer consumer;

    @GetMapping
    public List<Resident> findAll(){
        return residentService.findAll();
    }


    @PostMapping
    public ResponseEntity<Resident> save(@RequestBody Resident resident){
        Apartment ap = apartmentService.findOne(resident.getApartment().getId());
        resident.setApartment(ap);
        //producer.send("new register");
        return  new ResponseEntity<>(residentService.save(resident), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Resident> findOne(@PathVariable Long id){
        Resident b = residentService.findOne(id);
        return b !=  null ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        residentService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Resident> update(@PathVariable Long id, @RequestBody Block resident){
        Resident residentSalvo = residentService.findOne(id);
        if(residentSalvo == null)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(resident, residentSalvo, "id");
        residentService.save(residentSalvo);
        return ResponseEntity.ok(residentSalvo);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ap/{ap}")
    public List<Resident> listApartamentsByBlock(@PathVariable Long ap) {
        return residentService.listResidentByApartment(ap);
    }

}