package com.ifam.tccbackend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
public class Resident {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private		long		                id;

    @Getter
    @Setter
    private 	String						name;

    @Getter
    @Setter
    private 	String						sexo;

    @Getter
    @Setter
    private     Date                        dataNasc;

    @Getter
    @Setter
    @ManyToOne
    private     Apartment                   apartment;

    @Getter
    @Setter
    @OneToMany
    private     List<FileModel>             gallery;

}
