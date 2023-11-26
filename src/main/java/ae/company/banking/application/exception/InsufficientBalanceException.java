package ae.company.banking.application.exception;

public class InsufficientBalanceException extends RuntimeException {

	public InsufficientBalanceException() {
		super("The account balance is insufficient");
	}
}
