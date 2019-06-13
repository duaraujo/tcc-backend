package com.ifam.tccbackend.service;

import com.ifam.tccbackend.dto.BlockDTO;
import com.ifam.tccbackend.model.Block;
import com.ifam.tccbackend.repository.IBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private final IBlock iBlock;

    @Autowired
    public BlockService(IBlock iBlock){ this.iBlock = iBlock; }

    public List<Block> findAll(){ return iBlock.findAll(); }

    public Block findOne(Long id){ return this.iBlock.getOne(id); }

    public Block save(BlockDTO blockDTO){ return iBlock.save(blockDTO.getModel()); }

    public Block save(Block block){ return iBlock.save(block); }

    public void delete(Long id){ iBlock.deleteById(id);}

}