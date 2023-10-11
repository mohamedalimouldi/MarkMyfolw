package tn.esprit.usecase.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.usecase.Service.IMISSION;
import tn.esprit.usecase.Service.IPARTICIPATEDUSERS;
import tn.esprit.usecase.Service.ParticipatedUsersServiceImpl;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;
import tn.esprit.usecase.entity.StatusParticipation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/participatedusers")
public class ParticipatedUsersController {

    @Autowired
    IPARTICIPATEDUSERS iparticipatedusers;

    @GetMapping("")
    public List<ParticipatedUsers> getAllParticipatedUsers() {
        return iparticipatedusers.getAllParticipatedUsers();
    }

    @GetMapping("/mission/{id}")
    public List<ParticipatedUsers> getAllParticipatedUserByMissionId(@PathVariable Integer id) {
        return iparticipatedusers.getAllParticipationsByMissionId(id);
    }

    @GetMapping("/{id}")
    public ParticipatedUsers getParticipatedUserById(@PathVariable Integer id) {
        return iparticipatedusers.getParticipatedUserById(id);
    }

    @PostMapping("")
    public ParticipatedUsers createParticipatedUser(@RequestBody ParticipatedUsers participatedUser) {
        return iparticipatedusers.createParticipatedUser(participatedUser);
    }


    @PutMapping("/update/{id}")
    public ParticipatedUsers updateParticipatedUser(@PathVariable Integer id,@RequestBody ParticipatedUsers participatedUser) {
        return iparticipatedusers.updateParticipatedUser(id,participatedUser);
    }
    @PutMapping("/updatestatus/{id}/{status}")
    public ParticipatedUsers updateStatus(@PathVariable Integer id,@PathVariable StatusParticipation status) {
        return iparticipatedusers.updateStatus(id,status);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteParticipatedUser(@PathVariable Integer id) {
        iparticipatedusers.deleteParticipatedUserById(id);
    }

    // GET /users/{userId}/participation-counts
    @GetMapping("/participationStatus/{userId}")
    public Map<StatusParticipation, Long> getParticipationCountsForUser(@PathVariable int userId) {
        return iparticipatedusers.getCountsByStatusForUser(userId);

    }
    @GetMapping("/user/{id}/{missionid}")
    public Optional<ParticipatedUsers> getParticipatedUserByIdandmission(@PathVariable Integer id, @PathVariable Integer missionid) {
        return iparticipatedusers.getParticipatedUserandMission(id,missionid);
    }
}
