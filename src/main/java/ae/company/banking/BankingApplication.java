package ae.company.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableReactiveMongoRepositories (basePackageClasses = BankingApplication.class)
@SpringBootApplication
@EnableMongoAuditing
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run( BankingApplication.class, args);
	}

}
