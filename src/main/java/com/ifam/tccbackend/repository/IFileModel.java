package com.ifam.tccbackend.repository;

import com.ifam.tccbackend.model.FileData;
import com.ifam.tccbackend.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileModel extends JpaRepository<FileModel, Long> {

}
