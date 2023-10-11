package arcticmonkeys.tn.advisorms.repository;

import arcticmonkeys.tn.advisorms.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {

}
