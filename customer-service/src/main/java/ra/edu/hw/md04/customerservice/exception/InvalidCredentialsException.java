package ra.edu.hw.md04.customerservice.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("email or password incorrect");
    }
}
