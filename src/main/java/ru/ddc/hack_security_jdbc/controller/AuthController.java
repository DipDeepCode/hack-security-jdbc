package ru.ddc.hack_security_jdbc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ddc.hack_security_jdbc.controller.payload.ChangePasswordRequest;
import ru.ddc.hack_security_jdbc.controller.payload.DeleteRequest;
import ru.ddc.hack_security_jdbc.controller.payload.RegisterRequest;
import ru.ddc.hack_security_jdbc.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest request,
                                        BindingResult bindingResult) throws BindException {
        userService.createUser(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/auth")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                            BindingResult bindingResult) throws BindException {
        userService.changePassword(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/auth")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindingException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }
}
