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
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTORequest implements Serializable {

    @NonNull
    private String name;

    @Email
    @NonNull
    private String email;

    @NotBlank(message = "password is mandatory")
    @NonNull
    public String password;

    private List<Phone> phones;
}
