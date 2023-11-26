package ae.company.banking.application.exception;

public class BeneficirayAlreadyExistsException extends RuntimeException {

	public BeneficirayAlreadyExistsException() {
		super( "The beneficiary already exists ");
	}
}
