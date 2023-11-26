package ae.company.banking.startup;

import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.domain.user.entities.PersonalAccount;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repository.TransactionRepository;
import ae.company.banking.infrastructure.repository.UserRepository;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.List;
import java.util.Objects;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Startup {

	private static final Logger LOG = LoggerFactory.getLogger(Startup.class);


	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;

	@EventListener( ApplicationReadyEvent.class)
	public void initApplication() {

		transactionRepository.deleteAll().block();
		userRepository.deleteAll().block();

		LOG.info( "Init DB" );
		CurrencyUnit aed = Monetary.getCurrency("AED");
/*		String swissNumberStr = "044 668 18 00";
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();*/
		var user = User.builder().login( "login@test.com" )
				.id( "656315cc3b100507ed77d32a" )
				.firstName( "first" )
				.lastName( "last" )
				.password( "password" )
				.identityId( "545464" )
				.phoneNumber( PhoneNumberUtil.getInstance().getExampleNumber( "fr" ) )
				.accounts( List.of( PersonalAccount.builder()
								.id( ObjectId.get().toString() )
						.balance( Money.of( 200,aed ) )
								.type( AccountType.CURRENT )
								.bic( "BITETEE" )
								.bankAddress( "Luxembourg" )
								.iban( "LU6767678808808080" )
								.bankName( "ING" )
								.swift( "SWOOOP78" )
						.build()) )
				.build();
		userRepository.save( user ).subscribe(
				savedUser -> LOG.info("User saved successfully: {}", savedUser.getId()),
				error -> LOG.error("Error saving user", error)
		);
		LOG.info( "DB initialized" );
	}
}
