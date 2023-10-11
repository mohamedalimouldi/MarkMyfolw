package tn.esprit.usecase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.usecase.Repository.MissionRepository;
import tn.esprit.usecase.Repository.MissionTagsRepository;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.MissionTags;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionServiceImpl implements IMISSION {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    MissionTagsRepository missionTagsRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }


    public List<Mission> searchMissionByName(String title) {
        return missionRepository.findByTitleContaining(title);
    }

    public List<Mission> getMissionByTask(int task) {
        return  missionRepository.findByTask(task);
    }




    public List<Mission> getMissionByMissionTags(List<String> tagIds) {
        List<MissionTags> missionTags = missionTagsRepository.findByTagIdIn(tagIds);
        List<Integer> missionIds = missionTags.stream().map(mt -> mt.getMission().getIdMission()).collect(Collectors.toList());
        List<Mission> missions = missionRepository.findByIdMissionIn(missionIds);
        return missions;
    }

    public Mission getMissionById(Integer id) {
        return missionRepository.findById(id).orElse(null);
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public void deleteMission(Integer id) {
        missionRepository.deleteById(id);
    }

    public Mission updateMission(Integer id, Mission mission) {
                Mission existingMission = missionRepository.findById(id).orElse(null);
        if (existingMission != null) {
            existingMission.setTitleMission(mission.getTitleMission());
            existingMission.setDescriptionMission(mission.getDescriptionMission());
            existingMission.setDeadlineMission(mission.getDeadlineMission());
            existingMission.setPriceMission(mission.getPriceMission());
            existingMission.setTask(mission.getTask());
            return missionRepository.save(existingMission);
        }
        return null;
    }
}
