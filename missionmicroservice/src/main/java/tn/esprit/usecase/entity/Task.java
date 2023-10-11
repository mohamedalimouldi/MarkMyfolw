package tn.esprit.usecase.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTask")
    private Integer idTask; // Clé primaire

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private Set<Mission> missions;



}
