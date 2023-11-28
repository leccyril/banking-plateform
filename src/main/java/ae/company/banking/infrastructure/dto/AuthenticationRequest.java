package ae.company.banking.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = -6986746375915710855L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}