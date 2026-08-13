package template.restaurant.exception;

public class WithdrawForbiddenException extends RuntimeException {

    public WithdrawForbiddenException(String message) {
        super(message);
    }
}
