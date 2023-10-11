package tn.esprit.usecase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.usecase.Repository.MissionRepository;
import tn.esprit.usecase.Repository.ParticipatedUsersRepository;
import tn.esprit.usecase.entity.CompositeKey;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;
import tn.esprit.usecase.entity.StatusParticipation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParticipatedUsersServiceImpl implements IPARTICIPATEDUSERS {
    @Autowired
    private ParticipatedUsersRepository participatedUsersRepository;
    @Autowired
    private MissionRepository missionRepository;

    public ParticipatedUsers createParticipatedUser(ParticipatedUsers participatedUser) {

//        CompositeKey compositeKey = new CompositeKey();
        Mission mission = missionRepository.findById(participatedUser.getMission().getIdMission()).orElse(null); // fetch the Mission entity from the database using its ID
//        compositeKey.setIdMission(participatedUser.getMission().getIdMission()); // set the Mission object to the mission field in the CompositeKey object
//        //compositeKey.setIdPart(1);
//        participatedUser.setCompositeKey(compositeKey);
        Optional<ParticipatedUsers> existingParticipation = participatedUsersRepository.findByMissionAndUser(mission, participatedUser.getUser());
        if (existingParticipation.isPresent()) {
            throw new RuntimeException("Participation already exists for the given mission and user");
        }

        return participatedUsersRepository.save(participatedUser);
    }

    public ParticipatedUsers updateParticipatedUser(Integer id, ParticipatedUsers participatedUser) {
        ParticipatedUsers existingParticipatedUser = participatedUsersRepository.findById(id).orElse(null);
        if (existingParticipatedUser != null) {
            existingParticipatedUser.setDatePArticipation(participatedUser.getDatePArticipation());
            existingParticipatedUser.setStatus(participatedUser.getStatus());
            existingParticipatedUser.setMission(participatedUser.getMission());

            return participatedUsersRepository.save(existingParticipatedUser);
        }
        return null;
    }

    public ParticipatedUsers updateStatus(Integer id, StatusParticipation status) {
        ParticipatedUsers existingParticipatedUser = participatedUsersRepository.findById(id).orElse(null);
        if (existingParticipatedUser != null) {
            existingParticipatedUser.setStatus(status);
            return participatedUsersRepository.save(existingParticipatedUser);
        }
        return null;
    }

    public void deleteParticipatedUserById(Integer idPart) {
        participatedUsersRepository.deleteById(idPart);
    }

    public ParticipatedUsers getParticipatedUserById(Integer idPart) {
        return participatedUsersRepository.findById(idPart).orElse(null);
    }

    public List<ParticipatedUsers> getAllParticipationsByMissionId(int missionId) {
        return participatedUsersRepository.findAllByMission_IdMission(missionId);
    }

    @Override
    public Optional<ParticipatedUsers> getParticipatedUserandMission(int user, Integer mission) {
        return participatedUsersRepository.findByMission_IdMissionAndUser(mission,user);
    }


    public List<ParticipatedUsers> getAllParticipatedUsers() {
        return participatedUsersRepository.findAll();
    }

    public Map<StatusParticipation, Long> getCountsByStatusForUser(int userId) {
        List<ParticipatedUsers> participations = participatedUsersRepository.findAllByUser(userId);
        Map<StatusParticipation, Long> counts = new HashMap<>();
        for (ParticipatedUsers participation : participations) {
            StatusParticipation status = participation.getStatus();
            counts.put(status, counts.getOrDefault(status, 0L) + 1L);
        }
        return counts;
    }

    public Optional<ParticipatedUsers> getParticipatedUserandMission(int user, Mission mission) {
        return participatedUsersRepository.findByMissionAndUser(mission,user);
    }
}

