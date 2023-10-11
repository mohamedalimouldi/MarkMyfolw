package tn.esprit.usecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(CompositeKey.class)
public class ParticipatedUsers implements Serializable {
//    @EmbeddedId
//    private CompositeKey compositeKey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPart")
    private Integer idPart;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idPart;

    @Temporal (TemporalType.DATE)
    private Date datePArticipation;

    @Enumerated(EnumType.ORDINAL)
    private StatusParticipation status;


    @ManyToOne
    //@JsonIgnore
    //    @MapsId("idMission")
    @JoinColumn(name="idMission", nullable=false)
    private Mission mission;

//    @ManyToOne
//    @JoinColumn(name="idUser", nullable=true)
//    private User user;

      private int user;
}
