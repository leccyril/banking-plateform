package ae.company.banking.domain.user.usecases;

import ae.company.banking.infrastructure.repositories.UserRepositoryImpl;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CheckBalance {

	private final UserRepositoryImpl repository;

	public Mono<MonetaryAmount> execute(String userId, String accountId) {
		return repository.findAccountBalanceByUserIdAndAccountId( userId, accountId );
	}
}