package com.ifam.tccbackend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity(name = "files")
@DynamicUpdate
@DynamicInsert
public class FileData {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private		long		id;


    @Getter
    @Setter
    @Column(name = "path")
    private		String		path;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private		String		name;

    @Getter
    @Setter
    @Column(name = "extension", nullable = false)
    private		String		extension;

    @Getter
    @Setter
    @Column(name = "type", nullable = false)
    private 	EMediaType		type;



}