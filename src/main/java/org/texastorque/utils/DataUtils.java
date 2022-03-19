/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataUtils {

    private DataUtils() {
    }

    public static int toInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static double toDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String splitCamelCase(String s) {
        if (s == null || s.isEmpty()) return s;

        return s.replaceAll(
           String.format("%s|%s|%s",
              "(?<=[A-Z])(?=[A-Z][a-z])",
              "(?<=[^A-Z])(?=[A-Z])",
              "(?<=[A-Za-z])(?=[^A-Za-z])"
           ),
           " "
        );
    }

    public static String titleCase(String s) {
        if (s == null || s.isEmpty()) return s;

        return Arrays
            .stream(s.split(" "))
            .map(word -> word.isEmpty()
                ? word
                : Character.toTitleCase(word.charAt(0)) + word
                .substring(1)
                .toLowerCase())
            .collect(Collectors.joining(" "));
    }

    public static double round(double num, int dec) {
        return (int) Math.round((num * Math.pow(10, dec))) / Math.pow(10, dec);
    }

    public static String sha256String(String text) {
        try {
            return String.format("%064x", new BigInteger(1, 
                    MessageDigest.getInstance("SHA-256")
                    .digest(text.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
            return "";
        }
    }
}
