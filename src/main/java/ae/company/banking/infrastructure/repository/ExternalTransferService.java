package ae.company.banking.infrastructure.repository;

import ae.company.banking.infrastructure.dto.TransferDto;
import org.springframework.stereotype.Component;

@Component
public class ExternalTransferService implements ExternalTransfertRepository {
	@Override
	public void executeTransfert(TransferDto dto) {

	}
}
