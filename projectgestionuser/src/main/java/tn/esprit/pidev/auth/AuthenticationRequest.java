package tn.esprit.pidev.auth;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationRequest {
    private String username;
    private String password;


}
