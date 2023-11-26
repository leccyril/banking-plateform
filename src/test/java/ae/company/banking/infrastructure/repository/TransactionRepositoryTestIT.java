package ae.company.banking.infrastructure.repository;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionType;
import java.time.LocalDate;
import java.util.Date;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryContext;
import javax.money.NumberValue;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@DataMongoTest
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