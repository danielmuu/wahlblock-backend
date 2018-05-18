package de.devdanmu.wahlblockbackend.util;

import org.springframework.web.server.ServerErrorException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Note for next hashing of something.
 * There are other password hashing algorithms like PBKDF2 which are more secure.
 *
 * @author danmu
 * created on 2018-03-15
 */
public class SecretUtil {

    public static String getSha256Hash(final String publicKey) {
        try {
            return tryToGenerateSha256Hash(publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new ServerErrorException("Hash couldn't be generated."); // todo better exception handling
        }
    }

    private static String tryToGenerateSha256Hash(String publicKey) throws NoSuchAlgorithmException {
        byte[] salt = getSalt();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] bytes = messageDigest.digest(publicKey.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private static byte[] getSalt() {
        int saltSize = 16;
        byte[] salt = new byte[saltSize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }


}
