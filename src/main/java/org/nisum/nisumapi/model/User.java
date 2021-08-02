package org.nisum.nisumapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter @ToString @RequiredArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Email(message = "This email is not a valid.")
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @JsonIgnore
    @NotNull
    private String passwordHash;

    private String token;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime modified = LocalDateTime.now();

    private LocalDateTime lastLogin = LocalDateTime.now();

    @NotNull
    @Column(nullable = false)
    private boolean active = false;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @ToString.Exclude
    @JsonManagedReference //also, you can use @JsonIgnore annotation
    private List<Phone> phones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
