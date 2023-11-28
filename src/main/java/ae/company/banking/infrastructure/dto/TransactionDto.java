package ae.company.banking.infrastructure.dto;

import ae.company.banking.configuration.converters.MoneyDeserializer;
import ae.company.banking.domain.transaction.entities.TransactionStatus;
import ae.company.banking.domain.transaction.entities.TransactionType;
import ae.company.banking.domain.user.entities.BeneficiaryAccount;
import ae.company.banking.domain.user.entities.PersonalAccount;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
	private MonetaryAmount amount;
	private UserDto user;
	private boolean isInternal;
	private PersonalAccount account;
	private BeneficiaryAccount beneficiary;
}

