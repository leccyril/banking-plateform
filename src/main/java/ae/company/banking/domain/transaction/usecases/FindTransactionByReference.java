package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.infrastructure.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class FindTransactionByReference {

	private final TransactionRepository repository;

	public Mono<Transaction> getTransactionByReference(@NonNull String reference) {
		return repository.findByReference( reference );
	}
}
