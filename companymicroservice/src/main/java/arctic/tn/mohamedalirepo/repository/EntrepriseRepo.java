package arctic.tn.mohamedalirepo.repository;

import arctic.tn.mohamedalirepo.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntrepriseRepo extends JpaRepository<Entreprise, Long> {

    List<Entreprise> findByName(String name);




}
