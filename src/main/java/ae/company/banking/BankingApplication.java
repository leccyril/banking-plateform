package ae.company.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableReactiveMongoRepositories (basePackageClasses = BankingApplication.class)
@SpringBootApplication
@EnableMongoAuditing
@EnableWebFlux
@ConfigurationPropertiesScan
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run( BankingApplication.class, args);
	}
}