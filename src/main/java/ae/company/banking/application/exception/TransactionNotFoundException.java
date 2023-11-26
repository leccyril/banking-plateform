package ae.company.banking.application.exception;

public class TransactionNotFoundException extends RuntimeException {

	TransactionNotFoundException(String id) {
		super( "Could not find transaction " + id );
	}
}
