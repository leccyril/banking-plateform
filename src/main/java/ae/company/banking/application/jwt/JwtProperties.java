package ae.company.banking.application.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
	//TODO: better to user application properties
	private String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";
	private long validityInMs = 36000000; // 1h
}