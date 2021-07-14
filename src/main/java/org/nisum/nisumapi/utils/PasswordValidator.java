package org.nisum.nisumapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator
{
    private PasswordValidator() { }

    public static boolean validatePassword(final String password)
    {
        String pattern = "(?=.*[A-Z])(?=.*[a-z])(?=[0-9]{2})/g";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}