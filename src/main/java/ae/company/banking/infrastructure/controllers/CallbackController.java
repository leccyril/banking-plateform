package ae.company.banking.infrastructure.controllers;

import ae.company.banking.domain.transaction.entities.Transaction;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.domain.transaction.usecases.UpdateTransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping( "api/v1/callback" )
@RequiredArgsConstructor
@Validated
public class CallbackController {

	private UpdateTransactionStatus updateTransactionStatus;

	@PostMapping( "{reference}/{status}" )
	public Mono<Transaction> callbackTransfert(@PathVariable String reference, @PathVariable TransactionStatus status){
		return updateTransactionStatus.updateStatus( reference, status );
	}
}
