package tn.esprit.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.usecase.entity.Tags;
import tn.esprit.usecase.entity.savedSearches;
@Repository
public interface TagsRepository  extends JpaRepository<Tags, String> {
}
