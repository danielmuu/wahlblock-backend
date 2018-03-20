package de.devdanmu.wahlblockbackend.util;

import org.springframework.web.server.ServerErrorException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author danmu
 * created on 2018-03-15
 */
public class SecretUtil {

    private static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getSha256Hash(final String publicKey) {
        int saltSize = 256;
        byte[] salt = generateSalt(saltSize);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] bytes = messageDigest.digest(publicKey.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServerErrorException("Test"); // todo better exception handling
        }
    }

    private static byte[] generateSalt(final int len) {
        byte[] salt = new byte[len];
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < len; i++) {
            salt[i] = (byte) CHAR_SET.charAt(secureRandom.nextInt(CHAR_SET.length()));
        }
        return salt;
    }


}
