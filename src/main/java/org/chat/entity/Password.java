package org.chat.entity;

import jakarta.ws.rs.ProcessingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Password {
    private String value;

    public Password(String password) {
        this.value = hashPassword(password);
    }

    private String hashPassword(String pwd) {
        try {

            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashbytes);
        } catch (Exception ex) {
            throw new ProcessingException("Error hashing the password");
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
