package tn.esprit.usecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser; // Clé primaire

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ParticipatedUsers> participatedUsers;

//    @ManyToMany(mappedBy = "users")
//    @JsonIgnore
//    private Set<Mission> missionss=new LinkedHashSet<>();



}
