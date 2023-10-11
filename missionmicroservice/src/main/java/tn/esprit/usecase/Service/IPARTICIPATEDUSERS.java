package tn.esprit.usecase.Service;

import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;
import tn.esprit.usecase.entity.StatusParticipation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPARTICIPATEDUSERS {

    ParticipatedUsers createParticipatedUser(ParticipatedUsers participatedUser);

    ParticipatedUsers updateParticipatedUser(Integer id,ParticipatedUsers participatedUser);

    void deleteParticipatedUserById(Integer idPart);

    ParticipatedUsers getParticipatedUserById(Integer idPart);

    List<ParticipatedUsers> getAllParticipatedUsers();

    ParticipatedUsers updateStatus(Integer id, StatusParticipation status);
    public Map<StatusParticipation, Long> getCountsByStatusForUser(int userId);

    public List<ParticipatedUsers> getAllParticipationsByMissionId(int missionId) ;

    public Optional<ParticipatedUsers> getParticipatedUserandMission(int user, Integer mission);

    }
