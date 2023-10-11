package tn.esprit.usecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class savedSearches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSavedSearches")
    private Integer idSavedSearches;

    String userEmail;
    String savedTitle;

    String tags;

    Integer idUser;

}
