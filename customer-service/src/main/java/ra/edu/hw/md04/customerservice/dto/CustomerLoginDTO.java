package ra.edu.hw.md04.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerLoginDTO(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
