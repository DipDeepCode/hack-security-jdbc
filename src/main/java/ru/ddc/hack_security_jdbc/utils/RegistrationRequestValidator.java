package ru.ddc.hack_security_jdbc.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ddc.hack_security_jdbc.controller.payload.RegisterRequest;

@Component
@RequiredArgsConstructor
public class RegistrationRequestValidator implements Validator {
    private final JdbcUserDetailsManager userDetailsManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest request = (RegisterRequest) target;
        if (userDetailsManager.userExists(request.getUsername())) {
            errors.rejectValue("username", "", "username is already taken");
        }
    }
}
