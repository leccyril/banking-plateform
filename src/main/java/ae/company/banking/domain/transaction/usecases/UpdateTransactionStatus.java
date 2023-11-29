package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.application.exception.TransactionNotFoundException;
import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UpdateTransactionStatus {

	private final TransactionRepository repository;

	public Mono<Transaction> updateStatus(String id, TransactionStatus status) {
		return repository.findById(id)
				.flatMap(transaction -> {
					transaction.setStatus(status);
					return repository.save(transaction);
				})
				.switchIfEmpty(Mono.error(new TransactionNotFoundException(id)));
	}
}
