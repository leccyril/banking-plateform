package ae.company.banking.domain.transaction.usecases;

import ae.company.banking.application.exception.AccountNotFoundException;
import ae.company.banking.application.exception.InsufficientBalanceException;
import ae.company.banking.application.exception.TransferException;
import ae.company.banking.application.exception.UserNotFoundException;
import ae.company.banking.application.utils.TransactionUtils;
import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.domain.transaction.entities.TransactionType;
import ae.company.banking.infrastructure.dto.ExternalTransferDto;
import ae.company.banking.infrastructure.dto.TransferDto;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import ae.company.banking.infrastructure.repositories.ExternalTransfertRepository;
import ae.company.banking.infrastructure.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ExecuteTransfert {

	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final ExternalTransfertRepository transfertRepository;

	public Mono<Transaction> execute(@NonNull TransferDto transfertDto) {

		if( StringUtils.isEmpty( transfertDto.getUserId() ) ){
			throw new UserNotFoundException();
		}
		if( Objects.isNull( transfertDto.getAmount() ) ){
			throw new TransferException( "Transfer data could not be null" );
		}
		if( transfertDto.getAmount().isNegativeOrZero() ){
			throw new TransferException( "Transfer amount could not be 0 or negative" );
		}
		if( Objects.isNull( transfertDto.getOriginAccountId() ) ){
			throw new AccountNotFoundException( "Origin account not found" );
		}
		if( transfertDto.isInternal() ){
			if( StringUtils.isEmpty( transfertDto.getDestinationAccountId() ) ){
				throw new AccountNotFoundException( "Destination account not found" );
			}
		}else{
			if( StringUtils.isEmpty( transfertDto.getBeneficiaryAccountId() ) ){
				throw new AccountNotFoundException( "Beneficiary account not found" );
			}
		}
		var transaction = Transaction.builder()
				.reference( TransactionUtils.generateReference() )
				.type( TransactionType.TRANSFERT )
				.amount( transfertDto.getAmount() )
				.executionDate( LocalDate.now() )
				.isInternal( transfertDto.isInternal() )
				.status( TransactionStatus.IN_PROGRESS )
				.description( transfertDto.getDescription() ).build();

		return userRepository.findById( transfertDto.getUserId() )
				.flatMap( user -> {
					transaction.setUser( user );
					var accountOriginOptional = user.getPersonalAccounts().stream()
							.filter( account -> account.getId().equals( transfertDto.getOriginAccountId() ) )
							.findFirst();
					if( accountOriginOptional.isEmpty() ){
						return Mono.error( new AccountNotFoundException( "Internal origin account not found" + transfertDto.getOriginAccountId() ) );
					}

					var accountOrigin = accountOriginOptional.get();
					if( accountOrigin.getBalance().isLessThan( transfertDto.getAmount() ) ){
						return Mono.error( new InsufficientBalanceException() );
					}
					var newBalance = accountOrigin.getBalance().subtract( transfertDto.getAmount() );
					accountOrigin.setBalance( newBalance );
					Mono<Void> transferOperation;
					//Internal user transfer
					if( transaction.isInternal() ){
						var accountDestinationOptional = user.getPersonalAccounts().stream()
								.filter( account -> account.getId().equals( transfertDto.getDestinationAccountId() ) )
								.findFirst();
						if( accountDestinationOptional.isEmpty() ){
							return Mono.error( new AccountNotFoundException( "Internal destination account not found" + transfertDto.getDestinationAccountId() ) );
						}
						var accountDestination = accountDestinationOptional.get();
						transaction.setDestinationAccount( accountDestination );
						//Instead of using same dto we can use private record
						//transfert amount from one account to another
						var amount = accountDestination.getBalance().add( transfertDto.getAmount() );
						accountDestination.setBalance( amount );
						transaction.setStatus( TransactionStatus.COMPLETED );
						transferOperation = Mono.empty();
					}else{

						var beneficiaryOptional = user.getBeneficiaries().stream()
								.filter( beneficiary -> beneficiary.getId().equals( transfertDto.getBeneficiaryAccountId() ) )
								.findFirst();
						if( beneficiaryOptional.isEmpty() ){
							return Mono.error( new AccountNotFoundException( "Beneficiary account not found" + transfertDto.getBeneficiaryAccountId() ) );
						}
						var externalTransfertDto = ExternalTransferDto.builder()
								.reference( TransactionUtils.generateReference() )
								.date( LocalDate.now() )
								.amount( transfertDto.getAmount() )
								.description( transfertDto.getDescription() )
								.beneficiaryIban( beneficiaryOptional.get().getIban() )
								.originIban( accountOrigin.getIban() ).build();

						transaction.setDestinationBeneficiary( beneficiaryOptional.get() );
						//execute external transfert
						transferOperation = transfertRepository.executeTransfert( Mono.just( externalTransfertDto ) )
								.doOnSuccess( unusued -> transaction.setStatus( TransactionStatus.IN_PROGRESS ) )
								.doOnError( unusued -> transaction.setStatus( TransactionStatus.ERROR ) );
					}
					return transferOperation
							.then( userRepository.save( user ) )
							.then( Mono.just( transaction ) );
				} )
				.flatMap( transactionRepository::save )
				.switchIfEmpty( Mono.error( new UserNotFoundException() ) );
	}
}
