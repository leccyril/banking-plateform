package ae.company.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run( BankingApplication.class, args);
	}
}