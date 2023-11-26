package ae.company.banking.domain.user.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Address {
	private String street;
	private String city;
	private String zipCode;
	private String country;
}
