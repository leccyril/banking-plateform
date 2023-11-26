package ae.company.banking.domain.user.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter public class BeneficiaryAccount {
	@Setter
	private String id;
	private String bankName;
	private String bankAddress;
	private String iban;
	private String bic;
	private String swift;
	private String lastName;
	private String firstName;
}
