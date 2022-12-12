package com.project.Group1.Implementation;

import java.util.UUID;

public class PasswordGenerator {

    private static PasswordGenerator instance_PasswordGenerator = null;

    private PasswordGenerator() {
    }
    public String generatePassword() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");

    }
    public static PasswordGenerator getInstance() {
        if (instance_PasswordGenerator == null) {
            instance_PasswordGenerator = new PasswordGenerator();
        }
        return instance_PasswordGenerator;
    }
}
