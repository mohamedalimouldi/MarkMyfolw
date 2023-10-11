package arcticmonkeys.tn.advisorms.repository;

import arcticmonkeys.tn.advisorms.models.Intent;
import arcticmonkeys.tn.advisorms.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepo extends JpaRepository<Intent,Integer> {
}
