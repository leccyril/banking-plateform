package ae.company.banking.domain.user.usecases;

import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.repositories.UserRepository;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class FindUserDetails implements ReactiveUserDetailsService {

	private final UserRepository userRepository;

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return userRepository.findByUsername( username )
				.switchIfEmpty( Mono.defer( () -> Mono.error( new UsernameNotFoundException( "User Not Found" ) ) ) )
				.map( user -> new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						getAuthorities( user )
				) );
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		return List.of( new SimpleGrantedAuthority( user.getRole().name() ) );
	}
}