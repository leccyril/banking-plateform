package ae.company.banking.infrastructure.controllers;

import ae.company.banking.WebFluxControllerSecurityTestConfig;
import ae.company.banking.application.jwt.JwtTokenFilter;
import ae.company.banking.domain.user.entities.Role;
import ae.company.banking.domain.user.entities.User;
import ae.company.banking.domain.user.usecases.AddUserAccount;
import ae.company.banking.domain.user.usecases.AddUserBeneficiary;
import ae.company.banking.domain.user.usecases.FindAllUsers;
import ae.company.banking.domain.user.usecases.FindUserById;
import ae.company.banking.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
	void testGetAllEndpoint() {

		var user = User.builder()
				.id( "4787889456456456456frfrfrf87" )
				.identityId( "iyioyuy9y98y9y9" )
				.firstName( "firstname" )
				.lastName( "lastname" )
				.role( Role.USER )
				.username( "test@test.com" ).build();
		when( repository.findById( "4787889456456456456frfrfrf87" ) )
				.thenReturn( Mono.just( user ) );
		var testClient = WebTestClient.bindToController( controller ).webFilter( new SecurityContextServerWebExchangeWebFilter() )
				.apply( springSecurity() )
				.build();
		testClient
				.get().uri( "/api/v1/users/4787889456456456456frfrfrf87" )
				.exchange()
				.expectStatus().isOk()
				.expectBodyList( User.class )
				.hasSize( 1 );
		Mockito.verify( repository, times( 1 ) ).findById( "4787889456456456456frfrfrf87" );

	}
}
