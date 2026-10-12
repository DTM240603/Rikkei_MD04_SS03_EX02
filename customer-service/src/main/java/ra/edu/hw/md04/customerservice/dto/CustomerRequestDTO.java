package ra.edu.hw.md04.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
        @NotBlank String fullName,
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
