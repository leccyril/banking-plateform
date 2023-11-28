package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.application.exception.InsufficientBalanceException;
import ae.company.banking.application.exception.UserNotFoundException;
import ae.company.banking.application.utils.TransactionUtils;
import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.domain.transaction.entities.TransactionType;
import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.infrastructure.dto.InoutDto;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import ae.company.banking.infrastructure.repositories.UserRepository;
import java.time.LocalDate;
import javax.security.auth.login.AccountNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ExecuteWithdraw {

	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;

	public Mono<Transaction> execute(@NonNull InoutDto dto) {

		return userRepository.findById( dto.getUserId() ).flatMap( user -> {

					var accountOptional = user.getPersonalAccounts().stream()
							.filter( account -> account.getType() == AccountType.CURRENT )
							.findFirst();

					if( accountOptional.isEmpty() ){
						return Mono.error( new AccountNotFoundException() );
					}

					var account = accountOptional.get();
					if( account.getBalance().isLessThan( dto.getAmount() ) ){
						return Mono.error( new InsufficientBalanceException() );
					}

					account.setBalance( account.getBalance().subtract( dto.getAmount() ) );
					var transaction = Transaction.builder()
							.reference( TransactionUtils.generateReference() )
							.executionDate( LocalDate.now() )
							.type( TransactionType.WITHDRAW )
							.amount( dto.getAmount() )
							.status( TransactionStatus.IN_PROGRESS )
							.description( "withdraw" )
							.isInternal( true )
							.user( user )
							.account( account ).build();

					return userRepository.save( user )
							.then( Mono.just( transaction ) )
							.map( savedTransaction -> {
								savedTransaction.setStatus( TransactionStatus.COMPLETED );
								return savedTransaction;
							} )
							.onErrorResume( e -> {
								transaction.setStatus( TransactionStatus.ERROR );
								return Mono.just( transaction );
							} );
				} )
				.flatMap( transactionRepository::save )
				.switchIfEmpty( Mono.error( new UserNotFoundException() ) );
	}
}