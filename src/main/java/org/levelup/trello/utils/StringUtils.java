package org.levelup.trello.utils;

import java.util.Objects;

public class StringUtils {


    public static String reverse(String original) {
        Objects.requireNonNull(original);
        if (original.trim().isEmpty()) {
            return original;
        }
        return new StringBuilder(original).reverse().toString();
    }

    public static String concat (String s1, String s2) {
        return s1+s2;
    }

}
