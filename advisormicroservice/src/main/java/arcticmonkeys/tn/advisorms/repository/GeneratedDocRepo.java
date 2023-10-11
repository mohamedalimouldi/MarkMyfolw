package arcticmonkeys.tn.advisorms.repository;

import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.GeneratedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeneratedDocRepo extends JpaRepository<GeneratedDocument,Integer> {
}
