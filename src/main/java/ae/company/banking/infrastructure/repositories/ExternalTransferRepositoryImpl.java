package ae.company.banking.infrastructure.repositories;

import ae.company.banking.infrastructure.dto.ExternalTransferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExternalTransferRepositoryImpl implements ExternalTransfertRepository {
	@Override
	public Mono<Void> executeTransfert(Mono<ExternalTransferDto> dto) {
		log.info( "Transfert in progress" );
		return Mono.empty();
	}
}
