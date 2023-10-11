package tn.esprit.pidev.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Repository.RoleRepository;
import tn.esprit.pidev.entity.Role;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository ;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
