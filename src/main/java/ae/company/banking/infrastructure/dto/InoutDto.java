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
public class InoutDto {
	private String userId;
	@JsonDeserialize(using = MoneyDeserializer.class)
	private MonetaryAmount amount;
}

