package ae.company.banking.infrastructure.repositories;

import ae.company.banking.domain.user.entities.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,String> {

	Mono<Boolean> existsByBeneficiaries_firstNameAndBeneficiaries_lastNameAndId(String firstName, String lasName, String id);
	Mono<User> findByUsername(String username);
}
