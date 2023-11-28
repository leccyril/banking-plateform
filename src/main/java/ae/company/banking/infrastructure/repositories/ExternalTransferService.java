package ae.company.banking.infrastructure.repositories;

import ae.company.banking.infrastructure.dto.TransferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExternalTransferService implements ExternalTransfertRepository {
	@Override
	public void executeTransfert(TransferDto dto) {
		log.info( "Transfert in progress" );
	}
}
