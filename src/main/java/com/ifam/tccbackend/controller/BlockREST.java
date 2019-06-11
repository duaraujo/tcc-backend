package com.ifam.tccbackend.controller;


import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.repository.IBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blocks")
public class BlockREST {

    @Autowired
    private IBlock iBlock;

    @GetMapping
    public List<Block> findAll(){
        return iBlock.findAll();
    }


}