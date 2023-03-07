package com.microrent.backend.configs;

import com.microrent.backend.models.User;
import com.microrent.backend.repositories.UserRepository;
import com.microrent.backend.security.JwtUserDetails;
import com.microrent.backend.util.exceptions.UserDeactivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomIdentityAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public CustomIdentityAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            String rawPassword = password + user.get().getHashSalt();
            if(passwordEncoder.matches(rawPassword, user.get().getPassword())){
                UserDetails userDetails = new JwtUserDetails(user.get());
                if (userDetails.isEnabled()){
                    return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                }else {
                    throw new UserDeactivatedException("User deactivated!");
                }
            }
            else {
                throw new UsernameNotFoundException("Invalid email or password!");
            }
        }
        else {
            throw new UsernameNotFoundException("Invalid email or password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
