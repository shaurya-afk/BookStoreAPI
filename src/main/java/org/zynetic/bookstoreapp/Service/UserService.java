package org.zynetic.bookstoreapp.Service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zynetic.bookstoreapp.Configuration.JwtUtil;
import org.zynetic.bookstoreapp.Dto.UserDto;
import org.zynetic.bookstoreapp.Entity.User;
import org.zynetic.bookstoreapp.Repository.UserRepo;

@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserDto userDto) {
        if (userRepo.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        newUser.setPassword(hashedPassword);

        userRepo.save(newUser);

        return "User registered successfully.";
    }

    public String login(UserDto userDto) {
            User user = userRepo.findByUsername(userDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));

            if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Invalid username or password.");
            }

        return JwtUtil.generateToken(user);
        }
}
