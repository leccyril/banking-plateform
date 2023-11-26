package ae.company.banking.infrastructure.repository;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.user.entities.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction,String> {

	Flux<Transaction> findAllByUserOrderByExecutionDateDesc(User user);
	Mono<Transaction> findByReference(String reference);
}
