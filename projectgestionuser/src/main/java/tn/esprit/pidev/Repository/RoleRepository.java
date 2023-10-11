package tn.esprit.pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(" select r from Role r " +
            " where r.roleName = ?1")
    Role findRoleByRoleName(String rolename);

    
}
