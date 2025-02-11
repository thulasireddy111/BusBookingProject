package com.bus.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtility {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "YZ9xq9QzbeZQq7b51l+Uww==";
    // Method to encrypt a string
    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(KEY), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during encryption", e);
        }
    }

    // Method to decrypt a string
    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(KEY), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during decryption", e);
        }
    }
}
