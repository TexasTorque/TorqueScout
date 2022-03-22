/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(String.format("%064x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest((new Scanner(System.in)).next().getBytes(StandardCharsets.UTF_8)))));
    }
}
