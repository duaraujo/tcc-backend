package com.ifam.tccbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Apartment {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Block block;

    @Getter
    @Setter
    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private List<Resident> residents;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sendImage")
    private FileModel sendImage;

    public Apartment(String number, Block block, List<Resident> residents, FileModel sendImage) {
        this.number = number;
        this.block = block;
        this.residents = residents;
        this.sendImage = sendImage;
    }

    public Apartment(String number, Block block, FileModel sendImage) {
        this.number = number;
        this.block = block;
        this.sendImage = sendImage;
    }

    public Apartment(){}


}