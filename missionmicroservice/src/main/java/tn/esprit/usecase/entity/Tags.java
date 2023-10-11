package tn.esprit.usecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tags {
    @Id
    @Column(name="idTag")
    private String id;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<MissionTags> missionTags = new HashSet<>();
}
