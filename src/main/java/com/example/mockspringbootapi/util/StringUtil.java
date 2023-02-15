package com.example.mockspringbootapi.util;

import java.util.Objects;

public class StringUtil {
    public static Integer hashString(String string) {
        if(Objects.isNull(string))
            return 0;
        return Math.abs(string.hashCode() % 6);
    }
}
