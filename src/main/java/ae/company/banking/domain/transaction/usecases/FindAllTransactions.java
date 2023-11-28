package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class FindAllTransactions {

	private final TransactionRepository repository;

	public Flux<Transaction> findAllTransaction() {
		return repository.findAll();
	}
}
