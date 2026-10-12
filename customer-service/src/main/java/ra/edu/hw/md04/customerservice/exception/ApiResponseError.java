package ra.edu.hw.md04.customerservice.exception;

import java.time.LocalDateTime;

public record ApiResponseError(LocalDateTime timestamp, int status, String error, String message) {
}
