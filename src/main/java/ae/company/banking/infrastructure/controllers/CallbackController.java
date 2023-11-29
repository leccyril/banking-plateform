package ae.company.banking.infrastructure.controllers;

import ae.company.banking.application.jwt.JwtTokenProvider;
import ae.company.banking.infrastructure.dto.AuthenticationRequest;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//TODO: write response only in header
@RestController
@RequestMapping( "api/v1/transfert" )
@RequiredArgsConstructor
@Validated
public class CallbackController {
	private final JwtTokenProvider tokenProvider;
	private final ReactiveAuthenticationManager authenticationManager;

	@GetMapping( "/response" )
	public Mono<ResponseEntity<Map<String,String>>> login(
			@Valid @RequestBody Mono<AuthenticationRequest> authRequest) {
		return authRequest
				.flatMap( login -> this.authenticationManager
						.authenticate( new UsernamePasswordAuthenticationToken(
								login.getUsername(), login.getPassword() ) )
						.map( this.tokenProvider::createToken ) )
				.map( jwt -> {
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.add( HttpHeaders.AUTHORIZATION, "Bearer " + jwt );
					var tokenBody = Map.of( "access_token", jwt );
					return new ResponseEntity<>( tokenBody, httpHeaders, HttpStatus.OK );
				} );
	}
}
