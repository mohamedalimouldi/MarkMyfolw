package tn.esprit.pidev.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entity.User;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

  Token findTokenByToken(String t);
  Integer countAllByUser(User u);
  List<Token> findAllValidTokenByUser(User u);
  Token findTokenByUser(User u);
  Optional<Token> findByToken(String token);
  @Query("select t from Token t where t.expired=false and t.revoked=false")
  List<Token> findAllValidToken();

  @Query("select t from Token t where t.expired=true or t.revoked=true ")
  List<Token> findAllNoneValidToken();



  Token findDistinctByUserAndExpiredIsFalseAndRevokedIsFalse(User user);

//  @Query("update Token where  ")
//  @Query("select distinct t from Token t , User u where  t.user.idUser=:idUser and t.expired=false and t.revoked=false ")
//  Token retrieveUserFromToken(@Param("idUser") Long idUser);


}
