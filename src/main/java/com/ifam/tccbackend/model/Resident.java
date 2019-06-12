package com.ifam.tccbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
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
    private 	String						email;

    @Getter
    @Setter
    private 	String						gender;

    @Getter
    @Setter
    private 	String						phone;

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

    public Resident(String name, String email, String gender, String phone, Date dataNasc, Apartment apartment, List<FileModel> gallery) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.dataNasc = dataNasc;
        this.apartment = apartment;
        this.gallery = gallery;
    }
}