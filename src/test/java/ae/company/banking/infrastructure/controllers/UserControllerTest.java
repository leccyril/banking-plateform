package ae.company.banking.infrastructure.controllers;

import ae.company.banking.WebFluxControllerSecurityTestConfig;
import ae.company.banking.application.jwt.JwtTokenFilter;
import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.PersonalAccount;
import ae.company.banking.domain.user.entities.Role;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.domain.user.usecases.AddUserAccount;
import ae.company.banking.domain.user.usecases.AddUserBeneficiary;
import ae.company.banking.domain.user.usecases.FindAllUsers;
import ae.company.banking.domain.user.usecases.FindUserById;
import ae.company.banking.infrastructure.dto.UserDto;
import ae.company.banking.infrastructure.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.server.context.SecurityContextServerWebExchangeWebFilter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@AutoConfigureWebTestClient
@AutoConfigureRestDocs
@ExtendWith( SpringExtension.class )
@WebFluxTest( controllers = UserController.class )
@Import( { FindUserById.class, FindAllUsers.class, AddUserAccount.class, AddUserBeneficiary.class, WebFluxControllerSecurityTestConfig.class } )
class UserControllerTest {
	@Autowired
	UserController controller;

	@MockBean
	UserRepository repository;

	@MockBean
	JwtTokenFilter filter;

	@Test
	@WithMockUser
	void testGetgetUserById() {
		var user = User.builder()
				.id( "4787889456456456456frfrfrf582" )
				.identityId( "iyioyuy9y98y9y9" )
				.firstName( "firstname2" )
				.lastName( "lastname2" )
				.role( Role.USER )
				.username( "test2@test.com" ).build();
		when( repository.findById( "4787889456456456456frfrfrf87" ) )
				.thenReturn( Mono.just( user ) );

		var testClient = WebTestClient.bindToController( controller ).webFilter( new SecurityContextServerWebExchangeWebFilter() )
				.apply( springSecurity() )
				.build();
		testClient
				.get().uri( "/api/v1/users/4787889456456456456frfrfrf87" )
				.exchange()
				.expectStatus().isOk()
				.expectBodyList( UserDto.class )
				.hasSize( 1 );
		Mockito.verify( repository, times( 1 ) ).findById( "4787889456456456456frfrfrf87" );
	}

	@Test
	@WithMockUser
	void testPostCreateAccountById() {

		CurrencyUnit eur = Monetary.getCurrency( "EUR" );
		var user = User.builder()
				.id( "4787889456456456456frfrfrf87" )
				.identityId( "iyioyuy9y98y9y9" )
				.firstName( "firstname" )
				.lastName( "lastname" )
				.role( Role.USER )
				.accounts( new ArrayList<>( Arrays.asList( PersonalAccount.builder()
								.id( "656315cc3b100507ed88d32a" )
								.balance( Money.of( 200, eur ) )
								.type( AccountType.CURRENT )
								.bic( "BITETEE" )
								.bankAddress( "Luxembourg" )
								.iban( "LU6767678808895080" )
								.bankName( "ING" )
								.swift( "SWOOOP78" )
								.build(),
						PersonalAccount.builder()
								.id( "656315cc3b111507ed88d32b" )
								.balance( Money.of( 800, eur ) )
								.type( AccountType.SAVING )
								.bic( "BITETEE" )
								.bankAddress( "Luxembourg" )
								.iban( "LU6768978808895080" )
								.bankName( "ING" )
								.swift( "SWOOOP78" )
								.build() ) ) )
				.beneficiaries( List.of( BeneficiaryAccount.builder()
						.id( "787315cc3b100507ed88d32a" )
						.iban( "LU6768952808895080" )
						.bankName( "BNP" )
						.bankAddress( "France" )
						.swift( "SWBNPKOUIUI" )
						.firstName( "test destination" )
						.lastName( "test lastname" )
						.bic( "BNPJJHXXX" )
						.build() ) )
				.username( "test@test.com" ).build();
		when( repository.findById( "4787889456456456456frfrfrf87" ) )
				.thenReturn( Mono.just( user ) );
		when( repository.save( user ) )
				.thenReturn( Mono.just( user ) );
		var testClient = WebTestClient.bindToController( controller ).webFilter( new SecurityContextServerWebExchangeWebFilter() )
				.apply( springSecurity() )
				.build();
		testClient
				.post().uri( "/api/v1/users/4787889456456456456frfrfrf87/SAVING" )
				.exchange()
				.expectStatus().isCreated();
		//TODO: add custom serializer on test configuration
			/*	.expectBodyList( UserDto.class )
				.hasSize( 1 );*/
		Mockito.verify( repository, times( 1 ) ).save( user );
	}

}
