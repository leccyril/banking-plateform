package ae.company.banking.domain.user.entities;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document( collection = "users" )
public class User {

	@Id
	@Getter
	private String id;

	@Getter
	private String lastName;
	@Getter
	private String firstName;
	@Indexed( unique = true )
	@Getter
	private String username;
	@Getter
	private String password;
	private String identityId;
	private IdentityType identityType;
	@Getter
	private boolean blocked;
	@Getter
	private Role role;
	@Getter
	@Setter
	private Address address;
	@Getter
	private PhoneNumber phoneNumber;
	private List<PersonalAccount> accounts;
	private List<BeneficiaryAccount> beneficiaries;

	public void createAccount(PersonalAccount account) {
		this.getPersonalAccounts().add( account );
	}

	public void addBeneficiary(BeneficiaryAccount beneficiary) {
		this.getBeneficiaries().add( beneficiary );
	}

	public List<PersonalAccount> getPersonalAccounts() {
		if( accounts == null ){
			accounts =  new ArrayList<>();
		}
		return accounts;
	}

	public List<BeneficiaryAccount> getBeneficiaries() {
		if( beneficiaries == null ){
			beneficiaries = new ArrayList<>();
		}
		return beneficiaries;
	}
	@Override
	public boolean equals(Object o) {
		if( this == o ){
			return true;
		}
		if( o == null || getClass() != o.getClass() ){
			return false;
		}

		User user = (User) o;

		if( !lastName.equals( user.lastName ) ){
			return false;
		}
		if( !firstName.equals( user.firstName ) ){
			return false;
		}
		if( !username.equals( user.username ) ){
			return false;
		}
		return identityId.equals( user.identityId );
	}
	@Override
	public int hashCode() {
		int result = lastName.hashCode();
		result = 31 * result + firstName.hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + identityId.hashCode();
		return result;
	}
}
