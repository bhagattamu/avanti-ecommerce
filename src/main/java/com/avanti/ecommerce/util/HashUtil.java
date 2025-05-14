/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.util.Base64;

public class HashUtil {
    private static final int PBKDF2_ITERATIONS = 1000;
    private static final int HASH_BYTE_SIZE = 256;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateSalt() {
        byte[] bytes = new byte[512];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     *
     * @param password
     * @param salt
     * @return encrypted text
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public String generateHashForPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}
