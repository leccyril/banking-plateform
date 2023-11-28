package ae.company.banking.infrastructure.dto;

import ae.company.banking.configuration.converters.MoneyDeserializer;
import ae.company.banking.configuration.converters.MoneySerializer;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.domain.transaction.entities.TransactionType;
import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.PersonalAccount;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private String reference;
	private String description;
	private LocalDate executionDate;
	private TransactionStatus status;
	private TransactionType type;
	@JsonDeserialize(using = MoneyDeserializer.class)
	@JsonSerialize(using = MoneySerializer.class)
	private MonetaryAmount amount;
	private UserDto user;
	private boolean isInternal;
	private PersonalAccount originAccount;
	private PersonalAccount destinationAccount;
	private BeneficiaryAccount destinationBeneficiary;
}

