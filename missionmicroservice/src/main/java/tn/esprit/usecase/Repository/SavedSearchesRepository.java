package tn.esprit.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

@Repository
public interface SavedSearchesRepository extends JpaRepository<savedSearches, Integer> {

    @Query("SELECT DISTINCT s.userEmail FROM savedSearches s WHERE " +
            "CONCAT(' ', s.tags, ' ') LIKE %:#{#keywords[0]}%")
    List<String> findByKeywords(@Param("keywords") List<String> keywords);

    List<savedSearches> findByIdUser(Integer iduser);
}
