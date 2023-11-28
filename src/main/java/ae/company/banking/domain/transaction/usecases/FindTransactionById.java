package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class FindTransactionById {

	private final TransactionRepository repository;

	public Mono<Transaction> findTransactionById(@NonNull String id) {
		return repository.findById( id );
	}
}
