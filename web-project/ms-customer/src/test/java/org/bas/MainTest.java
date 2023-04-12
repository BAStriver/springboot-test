package org.bas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootTest
public class MainTest {

    @Test
    public void generatePassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("---------> " + UUID.randomUUID());
        System.out.println("---------> " + bCryptPasswordEncoder.encode("123456"));
    }

}
