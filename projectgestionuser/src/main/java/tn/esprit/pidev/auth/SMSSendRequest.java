package tn.esprit.pidev.auth;

import lombok.Data;

@Data
public class SMSSendRequest {
    String phone;
    String message;
}
