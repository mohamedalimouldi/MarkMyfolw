package tn.esprit.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.usecase.entity.MissionTags;

import java.util.Collection;
import java.util.List;

@Repository
public interface MissionTagsRepository extends JpaRepository<MissionTags, Integer> {
    List<MissionTags> findByTagIdIn(Collection<String> tagIds);
}
