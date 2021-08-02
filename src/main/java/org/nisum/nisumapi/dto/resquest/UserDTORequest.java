package org.nisum.nisumapi.dto.resquest;

import lombok.*;
import org.nisum.nisumapi.model.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDTORequest implements Serializable {

    @NonNull
    public String name;

    @Email
    @NonNull
    public String email;

    @NotBlank(message = "password is mandatory")
    // @Pattern(regexp = Constants.PASSWORD_REGEX, message = "")
    public String password;

    public List<Phone> phones;
}
