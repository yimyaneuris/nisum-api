package org.nisum.nisumapi.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter()
public class Properties {

    @Value("${email.validation}")
    private String emailValidation;

    @Value("${password.regexp}")
    private String regexp;

    @Value("${encrypted.password}")
    private String passEncrypt;
}
