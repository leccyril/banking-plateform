package ae.company.banking.domain.beneficiary.entities;

import ae.company.banking.domain.user.entities.PersonalAccount;
import lombok.Getter;
import org.springframework.data.annotation.Id;

public class Beneficiary {

	@Id
	@Getter
	private String id;

	@Getter
	private String lastName;
	@Getter
	private String firstName;

	@Getter
	private PersonalAccount account;
}
