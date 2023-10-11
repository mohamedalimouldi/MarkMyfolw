package com.example.testdevalidation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class testvalidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idv;
    private String titre;
    private Integer duree;
    private Integer pts;
    //@JsonIgnore
       // @Lob
    //private byte[]
    //private String image;
    @OneToMany(mappedBy = "validation" ,cascade =CascadeType.ALL)
    private List<question> questions;

}
