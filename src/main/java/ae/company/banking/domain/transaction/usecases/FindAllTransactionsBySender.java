package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class FindAllTransactionsBySender {

	private final TransactionRepository repository;

	public Flux<Transaction> findAllBySender(@NonNull User sender) {
		return repository.findAllByUserOrderByExecutionDateDesc( sender );
	}
}
