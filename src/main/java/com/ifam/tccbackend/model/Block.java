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
public class Block {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private		long		id;

    @Getter
    @Setter
    private 	String						name;

    @Getter
    @Setter
    private 	int						    floor;

    @Getter
    @Setter
    private 	Double						rating;

    @Getter
    @Setter
    private 	String						height;

    @Getter
    @Setter
    @OneToMany(mappedBy = "block")
    private List<Apartment>                 apartments;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sendImage")
    private FileModel 					sendImage;

    public Block() {
    }

    public Block(String name, int floor, Double rating, String height, FileModel sendImage) {
        this.name = name;
        this.floor = floor;
        this.rating = rating;
        this.height = height;
        this.sendImage = sendImage;
    }
}