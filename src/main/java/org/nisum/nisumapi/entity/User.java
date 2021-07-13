package org.nisum.nisumapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String token;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate last_login;
    private boolean isActive = false;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Phone> phones;
}
