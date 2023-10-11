package com.example.testdevalidation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idq;
    private String question;
    private String propvrai;

    @ManyToOne
    @JsonIgnore
    testvalidation validation;
    @JsonIgnore
    @OneToOne(mappedBy = "idq")
    reponse reponses;
    private String prop1;
    private String prop2;
    private String prop3;


}
