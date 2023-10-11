package tn.esprit.usecase.Service;

import tn.esprit.usecase.entity.MissionTags;

import java.util.List;
import java.util.Optional;

public interface IMISSIONTAGS {
    MissionTags addMissionTags(MissionTags missionTags);

    List<MissionTags> getAllMissionTags();

    Optional<MissionTags> getMissionTagById(Integer id);

    void deleteMissionTags(Integer id);
}
