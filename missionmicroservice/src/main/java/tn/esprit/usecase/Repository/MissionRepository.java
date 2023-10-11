package tn.esprit.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.usecase.entity.Mission;
import tn.esprit.usecase.entity.ParticipatedUsers;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission,Integer> {
    @Query("SELECT m FROM Mission m WHERE m.titleMission LIKE %:missionName% OR m.descriptionMission LIKE %:missionName% ")
    List<Mission> findByTitleContaining(@Param("missionName") String missionName);
    List<Mission> findByIdMissionIn(List<Integer> ids);
    List<Mission> findByTask(int task);
    @Query("SELECT m FROM Mission m JOIN m.missionTags mt WHERE mt.tag.id IN (:ids)")
    List<Mission> findByMissionTagss(@Param("ids") List<Integer> ids);
}
