package ae.company.banking.domain.user.entities;

import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter @Setter public class PersonalAccount {
	private String id;
	private String bankName;
	private String bankAddress;
	private String iban;
	private String bic;
	private String swift;
	private AccountType	type;
	private MonetaryAmount balance;
}
