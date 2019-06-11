package com.ifam.tccbackend.repository;

import com.ifam.tccbackend.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFile extends JpaRepository<FileData, Long> {

    FileData findById(long id);

}
