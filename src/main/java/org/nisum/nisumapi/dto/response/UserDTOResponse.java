package org.nisum.nisumapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nisum.nisumapi.model.Phone;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTOResponse implements Serializable {
    private Long id;

    private String name;

    private String email;

    private String token;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private boolean active;

    private List<Phone> phones;
}
