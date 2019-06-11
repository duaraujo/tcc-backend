package com.ifam.tccbackend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
public class Block {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private		long		id;

    @Getter
    @Setter
    @Column(nullable = false)
    private 	String						name;


    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sendImage")
    private FileData 					sendImage;




}