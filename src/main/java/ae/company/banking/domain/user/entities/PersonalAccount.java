package ae.company.banking.domain.user.entities;

import ae.company.banking.configuration.converters.MoneyDeserializer;
import ae.company.banking.configuration.converters.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter public class PersonalAccount {
	@Id
	private String id;
	private String bankName;
	private String bankAddress;
	private String iban;
	private String bic;
	private String swift;
	private AccountType	type;
	@JsonDeserialize(using = MoneyDeserializer.class)
	@JsonSerialize(using = MoneySerializer.class)
	private MonetaryAmount balance;
}
