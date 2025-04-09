package org.zynetic.bookstoreapp.Service;

    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import org.zynetic.bookstoreapp.Repository.UserRepo;

    @Service
    public class UserDetailServiceImpl implements UserDetailsService {
        private final UserRepo userRepo;

        public UserDetailServiceImpl(UserRepo userRepo) {
            this.userRepo = userRepo;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            org.zynetic.bookstoreapp.Entity.User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        }
    }