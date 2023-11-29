package ae.company.banking.infrastructure.repositories;

import ae.company.banking.infrastructure.dto.ExternalTransferDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExternalTransfertRepository {

	//Execute the external transfert
	Mono<Void> executeTransfert(Mono<ExternalTransferDto> dto);
}
