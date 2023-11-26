package ae.company.banking.application.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String id) {
		super( "Could not find account " + id );
	}

	public AccountNotFoundException() {
		super();
	}
}
