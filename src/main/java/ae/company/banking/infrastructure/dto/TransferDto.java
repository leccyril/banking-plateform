package ae.company.banking.infrastructure.dto;

import ae.company.banking.configuration.converters.MoneyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO: Create validator
public class TransferDto {
	private String description;
	@JsonDeserialize(using = MoneyDeserializer.class)
	private MonetaryAmount amount;
	private String userId;
	private boolean isInternal;
	private String originAccountId;
	private String destinationAccountId;
	private String beneficiaryAccountId;
	private String reference;
	private String originIban;
	private String beneficiaryIban;

}

