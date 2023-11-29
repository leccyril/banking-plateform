package ae.company.banking.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {

	@ResponseBody
	@ExceptionHandler( UserNotFoundException.class)
	@ResponseStatus( HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler( AccountNotFoundException.class)
	@ResponseStatus( HttpStatus.NOT_FOUND)
	String accountNotFoundHandler(AccountNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler( InsufficientBalanceException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST)
	String insufficientBalanceHandler(InsufficientBalanceException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler( TransferException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST)
	String transferErrorHandler(TransferException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler( BeneficirayAlreadyExistsException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST)
	String transferErrorHandler(BeneficirayAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler( Exception.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST)
	String transferErrorHandler(Exception ex) {
		return ex.getMessage();
	}

}
