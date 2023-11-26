package ae.company.banking.domain.user.usecases;

import ae.company.banking.application.exception.UserNotFoundException;
import ae.company.banking.domain.user.entities.Address;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class AddUserAddress {

	private final UserRepository repository;

	public Mono<User> execute(String userId, Address address) {
		return repository.findById( userId )
				.flatMap( user -> {
					user.setAddress( address );
					return repository.save( user );
				} )
				.switchIfEmpty( Mono.error( new UserNotFoundException( userId ) ) );
	}
}