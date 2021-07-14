package org.nisum.nisumapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator
{
    private EmailValidator() { }

    public static boolean validateEmail(final String email)
    {
        String pattern = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}