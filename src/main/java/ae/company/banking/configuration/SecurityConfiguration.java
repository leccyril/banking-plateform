package ae.company.banking.configuration;

import ae.company.banking.application.jwt.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CorsSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.LogoutSpec;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

	private final JwtTokenFilter jwtTokenFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager( userDetailsService );
		authenticationManager.setPasswordEncoder( passwordEncoder );
		return authenticationManager;
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange( exchanges -> exchanges
						.pathMatchers( HttpMethod.OPTIONS, "/**").permitAll()  // let the frontend preflight requests succeed without authentication.permitAll()
						.pathMatchers( "/actuator/**" ).permitAll()
						.pathMatchers( "/explorer/**" ).permitAll()
						.pathMatchers( "/api/v1/auth/login" ).permitAll()
						.pathMatchers( "/api/v1/transactions/{id}" ).hasAnyAuthority( "USER")
						.pathMatchers( "/api/v1/transactions/deposit" ).hasAnyAuthority( "USER")
						.pathMatchers( "/api/v1/transactions/withdraw" ).hasAnyAuthority( "USER")
						.pathMatchers( "/api/v1/transactions/transfer" ).hasAnyAuthority( "USER")
						//TODO: Implement Service role for external bank
						.pathMatchers( "/api/v1/callback/transfer" ).hasAnyAuthority( "SERVICE")
						.anyExchange().authenticated() )
				.formLogin( FormLoginSpec::disable )
				.logout( LogoutSpec::disable )
				.httpBasic( HttpBasicSpec::disable )
				.cors( CorsSpec::disable )
				.csrf( CsrfSpec::disable )
				.addFilterAt( jwtTokenFilter, SecurityWebFiltersOrder.AUTHENTICATION )
				.build();
	}
}