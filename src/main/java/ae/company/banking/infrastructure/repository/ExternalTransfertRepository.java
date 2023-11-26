package ae.company.banking.infrastructure.repository;

import ae.company.banking.infrastructure.dto.TransferDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalTransfertRepository {

	//Execute the external transfert
	void executeTransfert(TransferDto dto);
}
