package ae.company.banking.infrastructure.dto;

import ae.company.banking.domain.user.entities.Address;
import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.PersonalAccount;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
	private String firstName;
	private String lastName;
	private String fullName;
	private PhoneNumber phoneNumber;
	private Address address;
	private List<PersonalAccount> accounts;
	private List<BeneficiaryAccount> beneficiaries;
}
