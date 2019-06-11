package com.ifam.tccbackend.controller;


import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.repository.IBlock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/blocks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlockREST {

    @Autowired
    private IBlock iBlock;

    @RequestMapping(method= RequestMethod.GET)
    public List<Block> findAll(){
        return iBlock.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody  Block block){
        iBlock.save(block);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Block> findOne(@PathVariable Long id){
        Block b = iBlock.getOne(id);
        return b !=  null ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        iBlock.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Block> update(@PathVariable Long id, @RequestBody Block block){
        Block blockSalvo = iBlock.getOne(id);
        if(blockSalvo == null)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(block, blockSalvo, "id");
        iBlock.save(blockSalvo);
        return ResponseEntity.ok(blockSalvo);
    }


}