package org.nisum.nisumapi.repository;

import org.nisum.nisumapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select email from user e where email =: ")
    public boolean existsIfBlaBla(@Param("email") String email);
}
