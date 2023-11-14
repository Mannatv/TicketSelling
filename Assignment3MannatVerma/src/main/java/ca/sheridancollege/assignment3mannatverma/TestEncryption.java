package ca.sheridancollege.assignment3mannatverma;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncryption {
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
