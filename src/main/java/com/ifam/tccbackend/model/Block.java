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
public class Block {

    public Block(){
        super();
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int floor;

    @Getter
    @Setter
    private Double rating;

    @Getter
    @Setter
    private String height;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "block")
    private List<Apartment> apartments;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sendImage")
    private FileModel sendImage;

    public Block(String name, int floor, Double rating, String height, FileModel sendImage) {
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.sendImage = sendImage;
    }

    public Block(String name, int floor, Double rating, String height, List<Apartment> apartments) {
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.apartments = apartments;
    }
}