package ae.company.banking.infrastructure.controllers;

import ae.company.banking.application.mapper.BeneficiaryAccountMapper;
import ae.company.banking.application.mapper.UserMapper;
import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.domain.user.usecases.AddUserAccount;
import ae.company.banking.domain.user.usecases.AddUserBeneficiary;
import ae.company.banking.domain.user.usecases.CheckBalance;
import ae.company.banking.domain.user.usecases.FindAllUsers;
import ae.company.banking.domain.user.usecases.FindUserById;
import ae.company.banking.infrastructure.dto.BeneficiaryAccountDto;
import ae.company.banking.infrastructure.dto.UserDto;
import jakarta.validation.Valid;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
//TODO: fix bean validation
@Validated
@RequestMapping( "api/v1/users" )
public class UserController {

	private final FindAllUsers findAllUsers;
	private final FindUserById findUserById;
	private final AddUserAccount addAccount;
	private final AddUserBeneficiary addBeneficiary;
	private final CheckBalance checkBalance;

	@GetMapping
	@ResponseStatus( HttpStatus.ACCEPTED )
	Flux<UserDto> getAll() {
		return findAllUsers.findAll()
				.map( UserMapper::mapToUserDto )
				.switchIfEmpty( Flux.empty() );
	}

	@GetMapping( "{userId}" )
	@ResponseStatus( HttpStatus.OK )
	Mono<UserDto> getTransaction(@PathVariable String userId) {
		return findUserById.findById( userId ).map( UserMapper::mapToUserDto );
	}

	@PostMapping( "{userId}/{accountType}" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<UserDto> createAccount(@PathVariable String userId, @PathVariable AccountType accountType) {
		return addAccount.execute( userId, accountType ).map( UserMapper::mapToUserDto );
	}

	@PostMapping( "{userId}/beneficiaries" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<UserDto> addBeneficiary(@PathVariable String userId, @RequestBody @Valid BeneficiaryAccountDto dto) {
		return addBeneficiary.execute( userId, BeneficiaryAccountMapper.mapToAccount( dto ) ).map( UserMapper::mapToUserDto );
	}

	@GetMapping( "{userId}/balance/{accountId}" )
	@ResponseStatus( HttpStatus.OK )
	Mono<MonetaryAmount> getAccountBalance(@PathVariable String userId, @PathVariable String accountId) {
		return checkBalance.execute( userId, accountId );
	}
//TODO: to not forget to check if user is the same where execution have to be done
}
