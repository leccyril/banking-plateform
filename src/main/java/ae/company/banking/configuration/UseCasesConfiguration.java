package ae.company.banking.configuration;

import ae.company.banking.domain.transaction.usecases.ExecuteDeposit;
import ae.company.banking.domain.transaction.usecases.ExecuteTransfert;
import ae.company.banking.domain.transaction.usecases.ExecuteWithdraw;
import ae.company.banking.domain.transaction.usecases.FindAllTransactions;
import ae.company.banking.domain.transaction.usecases.FindTransactionById;
import ae.company.banking.domain.user.usecases.AddUserAccount;
import ae.company.banking.domain.user.usecases.AddUserBeneficiary;
import ae.company.banking.domain.user.usecases.FindAllUsers;
import ae.company.banking.domain.user.usecases.FindUserById;
import ae.company.banking.domain.user.usecases.FindUserDetails;
import ae.company.banking.infrastructure.repositories.ExternalTransfertRepository;
import ae.company.banking.infrastructure.repositories.TransactionRepository;
import ae.company.banking.infrastructure.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class UseCasesConfiguration {

	@Bean
	public FindUserDetails findUserDetails(UserRepository userRepository) {
		return new FindUserDetails( userRepository );
	}

	@Bean
	public FindAllUsers findAllUsers(UserRepository userRepository) {
		return new FindAllUsers( userRepository );
	}

	@Bean
	public FindUserById findUserById(UserRepository userRepository) {
		return new FindUserById( userRepository );
	}

	@Bean
	public AddUserAccount addUserAccount(UserRepository userRepository) {
		return new AddUserAccount( userRepository );
	}

	@Bean
	public AddUserBeneficiary addUserBeneficiary(UserRepository userRepository) {
		return new AddUserBeneficiary( userRepository );
	}

	@Bean
	public FindAllTransactions findAllTransactions(TransactionRepository transactionRepository) {
		return new FindAllTransactions( transactionRepository );
	}

	@Bean
	public FindTransactionById findTransactionById(TransactionRepository transactionRepository) {
		return new FindTransactionById( transactionRepository );
	}

	@Bean
	public ExecuteWithdraw executeWithdraw(TransactionRepository transactionRepository, UserRepository userRepository) {
		return new ExecuteWithdraw( transactionRepository, userRepository );
	}

	@Bean
	public ExecuteDeposit executeDeposit(TransactionRepository transactionRepository, UserRepository userRepository) {
		return new ExecuteDeposit( transactionRepository, userRepository );
	}

	@Bean
	@Transactional
	public ExecuteTransfert execute(TransactionRepository transactionRepository, UserRepository userRepository, ExternalTransfertRepository transferRepository) {
		return new ExecuteTransfert( transactionRepository, userRepository, transferRepository );
	}
}
