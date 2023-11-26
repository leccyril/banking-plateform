package ae.company.banking.infrastructure.repository;

import ae.company.banking.domain.user.entities.Address;
import ae.company.banking.domain.user.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith( SpringExtension.class )
class UserRepositoryTestIT {

	@Test
	void test(@Autowired UserRepository repository) {
		var user = User.builder()
				.firstName( "firstName" )
				.lastName( "lastname" )
				.address( new Address("street", "city", "L7889", "Luxembourg" ) )
				.login( "login@banking.lu" )
				.password( "test" )
				.build();

		repository.save( user ).block();
		var list = repository.findAll();
		StepVerifier.create( list )
				.expectSubscription()
				.expectNextCount( 1 )
				.verifyComplete();
	}
}