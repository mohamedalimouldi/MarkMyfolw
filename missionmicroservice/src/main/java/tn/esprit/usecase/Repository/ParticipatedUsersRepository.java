package tn.esprit.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.usecase.entity.CompositeKey;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipatedUsersRepository extends JpaRepository<ParticipatedUsers,Integer> {

     List<ParticipatedUsers> findAllByUser(int user);
     Optional<ParticipatedUsers> findByMissionAndUser(Mission mission, int user);
     Optional<ParticipatedUsers> findByMission_IdMissionAndUser(int mission, int user);
     List<ParticipatedUsers> findAllByMission_IdMission(int idMission);

}
