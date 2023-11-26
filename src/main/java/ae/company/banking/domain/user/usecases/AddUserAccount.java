package ae.company.banking.domain.user.usecases;

import ae.company.banking.application.exception.UserNotFoundException;
import ae.company.banking.application.utils.AccountUtils;
import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.domain.user.entities.PersonalAccount;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repository.UserRepository;
import javax.money.Monetary;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.javamoney.moneta.Money;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class AddUserAccount {

	private final UserRepository repository;

	public Mono<User> execute(String userId, AccountType type) {
		return repository.findById( userId )
				.flatMap( user -> {
					var account = PersonalAccount.builder()
							.id( ObjectId.get().toString() )
							.bic( "LUIIOO" )
							.iban( AccountUtils.generateIban() )
							.balance( Money.of( 0, Monetary.getCurrency( "EUR" ) ) ).build();

					switch( type ){
						case CURRENT -> account.setType( AccountType.CURRENT );
						case SAVING -> account.setType( AccountType.SAVING );
						case ISLAMIC -> account.setType( AccountType.ISLAMIC );
					}
					user.createAccount( account );
					return repository.save( user );
				} )
				.switchIfEmpty( Mono.error( new UserNotFoundException( userId ) ) );
	}
}