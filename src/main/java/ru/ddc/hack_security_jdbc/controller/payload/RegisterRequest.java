package ru.ddc.hack_security_jdbc.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 20, message = "the size should be in the range from {min} to {max}")
    private String username;

    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 120, message = "the size should be in the range from {min} to {max}")
    private String password;
}
