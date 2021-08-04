package org.nisum.nisumapi.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class Validations {

    @Autowired
    private final Properties properties;

    public boolean validatePassword(final String password) {
        Pattern p = Pattern.compile(properties.getRegexp());
        Matcher m = p.matcher(password);
        return m.matches();
    }
}