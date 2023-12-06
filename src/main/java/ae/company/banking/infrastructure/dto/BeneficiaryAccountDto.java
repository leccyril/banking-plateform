package ae.company.banking.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BeneficiaryAccountDto {

	@Size(min = 5)
	private String bankName;
	@Size(min = 20)
	private String bankAddress;
	@Size(max = 16)
	private String iban;
	@Size(max = 5)
	private String bic;
	@Size(max = 8)
	private String swift;
	@NotBlank(message = "lastName is mandatory")
	private String lastName;
	@NotNull(message = "firstName is mandatory")
	private String firstName;
}


