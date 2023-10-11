package tn.esprit.usecase.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.usecase.Service.IMISSIONTAGS;
import tn.esprit.usecase.entity.MissionTags;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missiontags")
public class MissionTagsRestController {
    @Autowired
    IMISSIONTAGS imissiontags;

    @PostMapping("")
    public MissionTags addMissionTags(@RequestBody MissionTags missionTags) {
        return imissiontags.addMissionTags(missionTags);
    }

    @GetMapping()
    public List<MissionTags> getAllMissionTags() {
        return imissiontags.getAllMissionTags();
    }

    @GetMapping("/{id}")
    public Optional<MissionTags> getMissionTagById(@PathVariable Integer id) {
        return imissiontags.getMissionTagById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMissionTags(@PathVariable Integer id) {
        imissiontags.deleteMissionTags(id);
    }
}
