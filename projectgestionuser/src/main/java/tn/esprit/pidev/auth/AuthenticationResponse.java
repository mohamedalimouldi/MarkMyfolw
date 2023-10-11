package tn.esprit.pidev.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
