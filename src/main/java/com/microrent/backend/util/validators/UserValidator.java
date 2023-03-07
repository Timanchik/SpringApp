package com.microrent.backend.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.microrent.backend.models.User;
import com.microrent.backend.repositories.UserRepository;

import javax.validation.constraints.NotNull;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
