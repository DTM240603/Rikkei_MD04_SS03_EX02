package ra.edu.hw.md04.customerservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("Email " + email + " đã được sử dụng.");
    }
}
