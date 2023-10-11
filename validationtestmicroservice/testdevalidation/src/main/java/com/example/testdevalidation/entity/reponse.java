package com.example.testdevalidation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idr;
    private String prop;

    @OneToOne
    //@JsonIgnore
    question idq;
    //@JsonIgnore
   // @ManyToOne
    private Integer iduserr;


}
