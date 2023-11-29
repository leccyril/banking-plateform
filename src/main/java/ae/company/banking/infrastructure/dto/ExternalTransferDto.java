package ae.company.banking.infrastructure.dto;

import ae.company.banking.configuration.converters.MoneyDeserializer;
import ae.company.banking.configuration.converters.MoneySerializer;
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
//TODO: Create validator
public class ExternalTransferDto {
	private String reference;
	private LocalDate date;
	private String description;
	@JsonDeserialize(using = MoneyDeserializer.class)
	@JsonSerialize(using = MoneySerializer.class)
	private MonetaryAmount amount;
	private String originIban;
	private String beneficiaryIban;
}

