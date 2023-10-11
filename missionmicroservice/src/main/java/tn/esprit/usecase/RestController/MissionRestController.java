package tn.esprit.usecase.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.usecase.Service.IMISSION;
import tn.esprit.usecase.Service.MissionServiceImpl;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionRestController {

    @Autowired
    IMISSION imission;

    @GetMapping
    public List<Mission> getAllMissions() {
        return imission.getAllMissions();
    }

    @GetMapping("/{id}")
    public Mission getMissionById(@PathVariable Integer id) {
        return imission.getMissionById(id);
    }
    @GetMapping("task/{id}")
    public List<Mission> getMissionByTask(@PathVariable Integer id) {
        return imission.getMissionByTask(id);
    }
    @PostMapping("/tags")
    public List<Mission> getMissionsByTags(@RequestBody List<String> tagIds) {
        return imission.getMissionByMissionTags(tagIds);
    }
    @PostMapping
    public Mission createMission(@RequestBody Mission mission) {
        return imission.createMission(mission);
    }

    @PostMapping("/search/{missionName}")
    public List<Mission> searchMission(@PathVariable String missionName) {
        return imission.searchMissionByName(missionName);
    }

    @PutMapping("/update/{id}")
    public Mission updateMission(@PathVariable Integer id, @RequestBody Mission mission) {
        return imission.updateMission(id, mission);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMission(@PathVariable Integer id) {
        imission.deleteMission(id);
    }
}
