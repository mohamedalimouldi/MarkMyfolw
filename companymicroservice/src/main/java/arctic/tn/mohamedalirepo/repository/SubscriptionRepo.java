package arctic.tn.mohamedalirepo.repository;
import arctic.tn.mohamedalirepo.entity.Entreprise;
import arctic.tn.mohamedalirepo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.From;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    Set<Subscription> findByDateFin(Date D);
    @Query("select e.name as entreprise, count (a.id) as numberSub, sum(a.prixAbon) as prixtotale from Subscription a " +
            "join a.entreprise e where a.dateDebut between :dateDebut and :dateFin " +
            "group by e.name")
    List<Object[]> getAbonnementsParEntreprise(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
    @Query("SELECT YEAR(a.dateDebut) as year, " +
            "CASE " +
            "WHEN MONTH(a.dateDebut) BETWEEN 1 AND 6 THEN '1-6' " +
            "ELSE '7-12' " +
            "END as semester, " +
            "MONTHNAME(a.dateDebut) as month, " +
            "SUM(a.prixAbon) as totalEarnings " +
            "FROM Subscription a " +
            "WHERE a.dateDebut BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(a.dateDebut), CASE WHEN MONTH(a.dateDebut) BETWEEN 1 AND 6 THEN '1-6' ELSE '7-12' END, MONTHNAME(a.dateDebut)")
    List<Object[]> getEarningsByYearSemesterAndMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



}





