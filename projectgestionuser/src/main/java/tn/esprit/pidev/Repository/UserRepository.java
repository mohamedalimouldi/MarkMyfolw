package tn.esprit.pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.pidev.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(" select u from User u " +
//            " where u.username = ?1")
    User findByUsername(String username);
//    @Query(" select u from User u " +
//            " where u.email = ?1")
//    User findByEmail(String username);
    User findByPhone(String phone);
    User findByEmail(String email);



    //User findByToken(String token);

    //

    //User findByCode(String code);

}

