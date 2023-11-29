package ae.company.banking.infrastructure.controllers;

import ae.company.banking.application.mapper.BeneficiaryAccountMapper;
import ae.company.banking.application.mapper.UserMapper;
import ae.company.banking.domain.user.entities.AccountType;
import ae.company.banking.domain.user.usecases.AddUserAccount;
import ae.company.banking.domain.user.usecases.AddUserBeneficiary;
import ae.company.banking.domain.user.usecases.FindAllUsers;
import ae.company.banking.domain.user.usecases.FindUserById;
import ae.company.banking.infrastructure.dto.BeneficiaryAccountDto;
import ae.company.banking.infrastructure.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping( "api/v1/users" )
public class UserController {

	private final FindAllUsers findAllUsers;
	private final FindUserById findUserById;
	private final AddUserAccount addAccount;
	private final AddUserBeneficiary addBeneficiary;

	@GetMapping
	@ResponseStatus( HttpStatus.ACCEPTED )
	Flux<UserDto> getAll() {
		return findAllUsers.findAll()
				.map( UserMapper::mapToUserDto )
				.switchIfEmpty( Flux.empty() );
	}

	@GetMapping( "{id}" )
	@ResponseStatus( HttpStatus.OK )
	Mono<UserDto> getTransaction(@PathVariable String id) {
		return findUserById.findById( id ).map( UserMapper::mapToUserDto );
	}

	@PostMapping( "{id}/{accountType}" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<UserDto> createAccount(@PathVariable String id, @PathVariable AccountType accountType) {
		return addAccount.execute( id, accountType ).map( UserMapper::mapToUserDto );
	}

	@PostMapping( "{id}/beneficiaries" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<UserDto> addBeneficiary(@PathVariable String id, @RequestBody BeneficiaryAccountDto dto) {
		return addBeneficiary.execute( id, BeneficiaryAccountMapper.mapToAccount( dto ) ).map( UserMapper::mapToUserDto );
	}
}
