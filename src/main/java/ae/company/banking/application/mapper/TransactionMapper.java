package ae.company.banking.application.mapper;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.infrastructure.dto.TransactionDto;

public class TransactionMapper {

	private TransactionMapper() {
		throw new AssertionError( "Could not be instantiate" );
	}

	public static TransactionDto mapToTransactionDto(Transaction transaction){
		return TransactionDto.builder()
				.reference( transaction.getReference() )
				.description( transaction.getDescription() )
				.status( transaction.getStatus() )
				.type( transaction.getType() )
				.executionDate( transaction.getExecutionDate() )
				.isInternal( transaction.isInternal() )
				.user( UserMapper.mapToUserDto(transaction.getUser()) )
				.beneficiary( transaction.getBeneficiary() )
				.account( transaction.getAccount() )
				.amount( transaction.getAmount() ).build();

	}

	public static Transaction mapToTransaction(TransactionDto transactionDto){
		return Transaction.builder()
				.reference( transactionDto.getReference() )
				.description( transactionDto.getDescription() )
				.status( transactionDto.getStatus() )
				.type( transactionDto.getType() )
				.executionDate( transactionDto.getExecutionDate() )
				.isInternal( transactionDto.isInternal() )
				.user( UserMapper.mapToUser(transactionDto.getUser()) )
				.beneficiary( transactionDto.getBeneficiary() )
				.account( transactionDto.getAccount() )
				.amount( transactionDto.getAmount() ).build();

	}
}
