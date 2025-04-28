package me.valless.dictionary.service.base;

import static java.lang.String.format;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.valless.dictionary.api.model.auth.SignUpRequest;
import me.valless.dictionary.entity.User;
import me.valless.dictionary.exception.AuthException;
import me.valless.dictionary.model.Role;
import me.valless.dictionary.repository.database.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("Пользователь '%s' не найден", username)));
        return mapToUserDetails(user);
    }

    public User signIn(String username, String password) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Неверные данные авторизации");
        }
        return user;
    }

    public String generateToken(String username, String password) {
        var token = username + ":" + password;
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    private CustomUserDetails mapToUserDetails(User user) {
        var details = new CustomUserDetails();
        details.setId(user.getId());
        details.setUsername(user.getUsername());
        details.setName(user.getName());
        details.setPassword(user.getPassword());
        details.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        return details;
    }

    public User signUp(SignUpRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthException("Пользователь с таким именем пользователя уже существует");
        }
        var user = User.builder()
                .role(Role.USER)
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Data
    public static class CustomUserDetails implements UserDetails {

        private Long id;
        private String username;
        private String password;
        private String name;
        private String email;
        private Collection<? extends GrantedAuthority> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
