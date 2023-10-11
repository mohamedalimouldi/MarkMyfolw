package tn.esprit.pidev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Email;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tn.esprit.pidev.entity.Role;
import tn.esprit.pidev.token.Token;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", length = 255)
    private Long idUser;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Column(nullable = false , unique = true)
    private String  username;
    @NotBlank
    private String password;
    //@Email
    //@Column(nullable = false , unique = true)
    @NotBlank
    private String email;
    //@Column(nullable = false , unique = true)
    @NotBlank
    private String phone;
    @NotBlank
   //@JsonIgnore
    private String code;
    @NotBlank
    private int isVerified;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Users_Role",
            joinColumns = @JoinColumn(name="iduser"),
            inverseJoinColumns = @JoinColumn(name = "idrole")
    )
    private Set<Role> Roles;

   @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Role role : Roles) {
            if (role !=null && role.getRoleName() != null)
            {authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println("Access Granted! am Here testing the authorities");}
            else
                System.out.println("Access Denied! Non authorized user!");
        }
        return authorities;
    }
    public void setRoles(Set<Role> roles) {
        Roles = roles;
    }
    public String getPassword() {
        return password;
    }

}
