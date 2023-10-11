package com.example.testdevalidation.entity;

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
public class passsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idp;
    private Integer score;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_test")
    private testvalidation test;
   // @JsonIgnore
    //@ManyToOne
    //@JoinColumn(name = "id_user")
    private Integer iduser;
    private String resultat;
}
