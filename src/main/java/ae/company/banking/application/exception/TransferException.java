package ae.company.banking.application.exception;

public class TransferException extends RuntimeException {

	public TransferException(String message) {
		super( message );
	}
}
