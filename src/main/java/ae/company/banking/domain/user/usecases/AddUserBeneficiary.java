package ae.company.banking.domain.user.usecases;

import ae.company.banking.application.exception.BeneficirayAlreadyExistsException;
import ae.company.banking.application.exception.UserNotFoundException;
import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class AddUserBeneficiary {

	private final UserRepository repository;

	public Mono<User> execute(String userId, BeneficiaryAccount account) {
		return repository.findById( userId )
				.flatMap( user -> repository.existsByBeneficiaries_firstNameAndBeneficiaries_lastNameAndId( account.getFirstName(), account.getLastName(), userId )
						.flatMap( exists -> {
							if( exists ){
								return Mono.error( new BeneficirayAlreadyExistsException() );
							}else{
								account.setId( ObjectId.get().toString() );
								user.addBeneficiary( account );
								return repository.save( user );
							}
						} ) )
				.switchIfEmpty( Mono.error( new UserNotFoundException( userId ) ) );
	}
}