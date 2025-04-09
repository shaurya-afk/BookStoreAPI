package org.zynetic.bookstoreapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zynetic.bookstoreapp.Entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
