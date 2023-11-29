package ae.company.banking.infrastructure.repositories;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionType;
import java.time.LocalDate;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith( SpringExtension.class )
@TestInstance( TestInstance.Lifecycle.PER_CLASS)
class TransactionRepositoryTestIT {

	@BeforeAll
	void deleteAll(@Autowired TransactionRepository repository) {
		repository.deleteAll().block();
	}
	@Test
	void test(@Autowired TransactionRepository repository) {
		CurrencyUnit aed = Monetary.getCurrency("AED");
		var transaction = Transaction.builder()
				.amount( Money.of(1000,aed) )
				.description( "test" )
				.type( TransactionType.TRANSFERT )
				.reference( "test" )
				.executionDate( LocalDate.now() ).build();

		repository.save( transaction ).block();
		var list = repository.findAll();
		StepVerifier.create( list )
				.expectSubscription()
				.expectNextCount( 1 )
				.verifyComplete();
	}
}