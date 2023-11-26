package ae.company.banking.application.mapper;

import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.infrastructure.dto.BeneficiaryAccountDto;

public class BeneficiaryAccountMapper {

	private BeneficiaryAccountMapper() {
		throw new AssertionError( "Could not be instantiate" );
	}

	public static BeneficiaryAccount mapToAccount(BeneficiaryAccountDto dto) {
		return BeneficiaryAccount.builder()
				.bankName( dto.getBankName() )
				.iban( dto.getIban() )
				.bic( dto.getBic() )
				.bankAddress( dto.getBankAddress() )
				.swift( dto.getSwift() )
				.lastName( dto.getLastName() )
				.firstName( dto.getFirstName() ).build();
	}
}
