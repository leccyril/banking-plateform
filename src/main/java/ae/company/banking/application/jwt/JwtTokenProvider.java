package ae.company.banking.application.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import static java.util.stream.Collectors.joining;

//TODO: rewrite the code to be cleaner and new jwt api
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
	private static final String AUTHORITIES_KEY = "roles";
	private final JwtProperties jwtProperties;
	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		var secret = Base64.getEncoder()
				.encodeToString( this.jwtProperties.getSecretKey().getBytes() );
		this.secretKey = Keys.hmacShaKeyFor( secret.getBytes( StandardCharsets.UTF_8 ) );
	}

	public String createToken(Authentication authentication) {

		Date now = new Date();
		Date validity = new Date( now.getTime() + this.jwtProperties.getValidityInMs() );

		String username = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();

		return Jwts.builder()
				.header().add( "typ", "JWT" ).and()
				.issuer( "banking" )
				.subject( username )
				.claim( AUTHORITIES_KEY, authorities.stream()
						.map( GrantedAuthority::getAuthority ).collect( joining( "," ) ) )
				.setAudience( System.getenv( "JWT_AUDIENCE" ) ) // Use deprecated method as Jitsi is not parsing when aud is an array.
				.issuedAt( now )
				.expiration( validity )  // IF endTime is not set generate token valid for 24 hours
				.signWith( this.secretKey, SignatureAlgorithm.HS256 )
				.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().setSigningKey( this.secretKey ).build()
				.parseClaimsJws( token ).getBody();

		Object authoritiesClaim = claims.get( AUTHORITIES_KEY );

		Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null
				? AuthorityUtils.NO_AUTHORITIES
				: AuthorityUtils
				.commaSeparatedStringToAuthorityList( authoritiesClaim.toString() );

		var principal = new User( claims.getSubject(), "", authorities );

		return new UsernamePasswordAuthenticationToken( principal, token, authorities );
	}

	public boolean validateToken(String token) {
		try{
			Jws<Claims> claims = Jwts.parser().setSigningKey( this.secretKey )
					.build().parseClaimsJws( token );
			// parseClaimsJws will check expiration date. No need do here.
			log.info( "expiration date: {}", claims.getBody().getExpiration() );
			return true;
		}catch(JwtException | IllegalArgumentException e){
			log.info( "Invalid JWT token: {}", e.getMessage() );
			log.trace( "Invalid JWT token trace.", e );
		}
		return false;
	}
}