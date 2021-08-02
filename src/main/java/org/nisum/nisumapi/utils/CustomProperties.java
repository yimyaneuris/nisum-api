package org.nisum.nisumapi.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter()
public class CustomProperties {

    @Value("${email.validation}")
    private String emailValidation;
}
