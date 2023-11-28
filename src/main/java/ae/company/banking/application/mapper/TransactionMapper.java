package ae.company.banking.application.mapper;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.infrastructure.dto.TransactionDto;

public class TransactionMapper {

	private TransactionMapper() {
		throw new AssertionError( "Could not be instantiate" );
	}

	public static TransactionDto mapToTransactionDto(Transaction transaction) {
		return TransactionDto.builder()
				.reference( transaction.getReference() )
				.description( transaction.getDescription() )
				.status( transaction.getStatus() )
				.type( transaction.getType() )
				.executionDate( transaction.getExecutionDate() )
				.isInternal( transaction.isInternal() )
				.user( UserMapper.mapToUserDto( transaction.getUser() ) )
				.destinationBeneficiary( transaction.getDestinationBeneficiary() )
				.originAccount( transaction.getOriginAccount() )
				.destinationAccount( transaction.getDestinationAccount() )
				.destinationBeneficiary( transaction.getDestinationBeneficiary() )
				.amount( transaction.getAmount() ).build();

	}

	public static Transaction mapToTransaction(TransactionDto transactionDto) {
		return Transaction.builder()
				.reference( transactionDto.getReference() )
				.description( transactionDto.getDescription() )
				.status( transactionDto.getStatus() )
				.type( transactionDto.getType() )
				.executionDate( transactionDto.getExecutionDate() )
				.isInternal( transactionDto.isInternal() )
				.user( UserMapper.mapToUser( transactionDto.getUser() ) )
				.destinationBeneficiary( transactionDto.getDestinationBeneficiary() )
				.originAccount( transactionDto.getOriginAccount() )
				.destinationAccount( transactionDto.getDestinationAccount() )
				.destinationBeneficiary( transactionDto.getDestinationBeneficiary() )
				.amount( transactionDto.getAmount() ).build();

	}
}
