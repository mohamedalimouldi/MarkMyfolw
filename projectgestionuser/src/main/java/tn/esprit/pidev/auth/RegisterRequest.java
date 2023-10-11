package tn.esprit.pidev.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
