package com.ifam.tccbackend.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifam.tccbackend.dto.BlockDTO;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.service.BlockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/rest/blocks")
public class BlockREST {

    @Autowired
    private BlockService blockService;

    @GetMapping
    public List<Block> findAll(){
        return blockService.findAll();
    }

    @PostMapping
    public ResponseEntity<Block> save(@RequestBody BlockDTO block){
        return  new ResponseEntity<>(blockService.save(block), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Block> findOne(@PathVariable Long id){
        Block b = blockService.findOne(id);
        return b !=  null ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        blockService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Block> update(@PathVariable Long id, @RequestBody Block block){
        Block blockSalvo = blockService.findOne(id);
        if(blockSalvo == null)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(block, blockSalvo, "id");
        blockService.save(blockSalvo);
        return ResponseEntity.ok(blockSalvo);
    }

}