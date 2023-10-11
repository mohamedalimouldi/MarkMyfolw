package tn.esprit.usecase.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMission")
    private Integer idMission; // Clé primaire

    String titleMission;
    String descriptionMission;

    @Temporal (TemporalType.DATE)
    private Date deadlineMission;

    Double priceMission;

//    @ManyToOne
//    @JoinColumn(name="idTask", nullable=true)
//    private Task task;
    private int task;



    @OneToMany(mappedBy = "mission")
    @JsonIgnore
    private Set<ParticipatedUsers> participatedUsers;

//    @ManyToMany
//    @JsonIgnore
//    @JoinTable(name = "ParticipatedUsers", joinColumns = @JoinColumn(name = "id_mission"), inverseJoinColumns = @JoinColumn(name = "id_user"))
//    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MissionTags> missionTags = new HashSet<>();

}
