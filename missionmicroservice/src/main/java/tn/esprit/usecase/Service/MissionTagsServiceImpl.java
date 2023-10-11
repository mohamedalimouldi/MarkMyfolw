package tn.esprit.usecase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.usecase.Repository.MissionTagsRepository;
import tn.esprit.usecase.entity.MissionTags;

import java.util.List;
import java.util.Optional;

@Service
public class MissionTagsServiceImpl implements IMISSIONTAGS{


    @Autowired
    private MissionTagsRepository missionTagsRepository;

    public MissionTags addMissionTags(MissionTags missionTags) {
        return missionTagsRepository.save(missionTags);
    }

    public List<MissionTags> getAllMissionTags() {
        return missionTagsRepository.findAll();
    }

    public Optional<MissionTags> getMissionTagById(Integer id) {
        return missionTagsRepository.findById(id);
    }

    public void deleteMissionTags(Integer id) {
        missionTagsRepository.deleteById(id);
    }
}
