package tn.esprit.usecase.Service;

import tn.esprit.usecase.entity.Mission;

import java.util.List;

public interface IMISSION {
    List<Mission> getAllMissions();
    List<Mission> searchMissionByName(String title);
    List<Mission> getMissionByTask(int task);

    List<Mission> getMissionByMissionTags(List<String> tagIds);

    Mission getMissionById(Integer id);

    Mission createMission(Mission mission);

    Mission updateMission(Integer id, Mission mission);

    void deleteMission(Integer id);


}
