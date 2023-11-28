package ae.company.banking.domain.transaction.entities;

import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.PersonalAccount;
import ae.company.banking.domain.user.entities.User;
import java.time.LocalDate;
import javax.money.MonetaryAmount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document( collection = "transactions" )
@Getter public class Transaction extends Auditable {

	@Id
	@Getter
	private String id;

	@Setter
	@Indexed(unique = true)
	private String reference;
	private String description;
	@Setter
	private LocalDate executionDate;
	@Setter
	private TransactionStatus status;
	@Setter
	private TransactionType type;
	private boolean archived;
	private boolean isInternal;
	private MonetaryAmount amount;
	@DBRef
	@Setter
	private User user;
	@Setter
	private PersonalAccount originAccount;
	@Setter
	private PersonalAccount destinationAccount;
	@Setter
	private BeneficiaryAccount destinationBeneficiary;
}

