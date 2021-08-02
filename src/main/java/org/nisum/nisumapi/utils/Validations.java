package org.nisum.nisumapi.utils;

import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    public static boolean validatePassword(final String password)
    {
        Pattern p = Pattern.compile(Constants.PASSWORD_REGEX);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}