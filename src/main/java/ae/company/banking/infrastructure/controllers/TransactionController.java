package ae.company.banking.infrastructure.controllers;

import ae.company.banking.application.mapper.TransactionMapper;
import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.usecases.ExecuteDeposit;
import ae.company.banking.domain.transaction.usecases.ExecuteTransfert;
import ae.company.banking.domain.transaction.usecases.ExecuteWithdraw;
import ae.company.banking.domain.transaction.usecases.FindTransactionById;
import ae.company.banking.infrastructure.dto.TransactionDto;
import ae.company.banking.domain.transaction.usecases.FindAllTransactions;
import ae.company.banking.infrastructure.dto.TransferDto;
import ae.company.banking.infrastructure.dto.inoutDto;
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
@RequestMapping( "api/v1/transactions" )
public class TransactionController {

	private final FindAllTransactions findAll;
	private final FindTransactionById findById;
	private final ExecuteTransfert transfer;
	private final ExecuteDeposit deposit;
	private final ExecuteWithdraw withdraw;

	@GetMapping
	@ResponseStatus( HttpStatus.ACCEPTED )
	Flux<TransactionDto> getAllTransactions() {
		Flux<Transaction> catalogFlux = findAll.findAllTransaction();
		return catalogFlux
				.map( TransactionMapper::mapToTransactionDto )
				.switchIfEmpty( Flux.empty() );
	}

	@GetMapping( "{id}" )
	@ResponseStatus( HttpStatus.ACCEPTED )
	Mono<TransactionDto> getTransaction(@PathVariable String id) {
		return findById.findTransactionById( id ).map( TransactionMapper::mapToTransactionDto );
	}

	@PostMapping( "/deposit" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<TransactionDto> deposit(@RequestBody inoutDto dto) {
		return deposit.execute( dto ).map( TransactionMapper::mapToTransactionDto );
	}

	@PostMapping( "/withdraw" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<TransactionDto> withdraw(@RequestBody inoutDto dto) {
		return withdraw.execute( dto ).map( TransactionMapper::mapToTransactionDto );
	}

	@PostMapping( "/transfer" )
	@ResponseStatus( HttpStatus.CREATED )
	Mono<TransactionDto> transfer(@RequestBody TransferDto dto) {
		return transfer.execute( dto ).map( TransactionMapper::mapToTransactionDto );
	}

}
