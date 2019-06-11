package com.ifam.tccbackend.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
public class Apartment {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private		long		                id;

    @Getter
    @Setter
    private 	String						number;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private     Block 					    block;

    @Getter
    @Setter
    @OneToMany
    private List<Resident>                   residents;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sendImage")
    private     FileModel 		   			 sendImage;

}