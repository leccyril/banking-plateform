package ae.company.banking.domain.user.usecases;

import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class FindUserById {

	private final UserRepository repository;

	public Mono<User> findById(String id) {
		return repository.findById( id );
	}
}
