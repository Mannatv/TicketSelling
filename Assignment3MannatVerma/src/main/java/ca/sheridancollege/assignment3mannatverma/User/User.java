package ca.sheridancollege.assignment3mannatverma.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private long userId;
    private String  userName ;
    private String encryptedPassword;
    private byte ENABLED;
}
