package com.example.courses.Helper;

import jakarta.validation.constraints.Null;

import java.util.List;

public class HelperUtils {
    public static <T> Boolean isNull(T str) {
        return null == str;
    }

    public static <T> Boolean isNotNull(T str) {
        return !isNull(str);
    }

    public static <T> Boolean isListEmpty(List<T> strList) {
        return strList.isEmpty();
    }

    public static <T> Boolean isListNotEmpty(List<T> strList) {
        return !isListEmpty(strList);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static <T> Boolean isListNull(List<T> strList) {
        return strList == null;
    }

    public static <T> Boolean isListNotNull(List<T> strList) {
        return !isListNull(strList);
    }

    public static Double limitScore(Double score) throws Exception {

        if (isNull(score)) {
            throw new Exception("Score cannot be null");
        }

        if (score < 0 || score > 100) {
            throw new Exception("Score must be between 0 and 100");
        }

        return score;
    }

}
