package ae.company.banking.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BeneficiaryAccountDto {
	private String bankName;
	private String bankAddress;
	private String iban;
	private String bic;
	private String swift;
	private String lastName;
	private String firstName;
}


