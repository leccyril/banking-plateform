package ae.company.banking.infrastructure.repositories;

import ae.company.banking.infrastructure.dto.ExternalTransferDto;
import ae.company.banking.infrastructure.dto.InternalTransferDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExternalTransfertRepository {

	//Execute the external transfert
	Mono<Void> executeTransfert(Mono<ExternalTransferDto> dto);
}
