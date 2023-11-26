package ae.company.banking.application.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String id) {
		super( "Could not find user " + id );
	}

}
