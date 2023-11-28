package ae.company.banking.domain.user.usecases;

import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class FindAllUsers {

	private final UserRepository repository;

	public Flux<User> findAll() {
		return repository.findAll();
	}
}
