package ae.company.banking.configuration;


import ae.company.banking.BankingApplication;
import ae.company.banking.configuration.converters.MoneyReadConverter;
import ae.company.banking.configuration.converters.MoneyWriteConverter;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackageClasses = BankingApplication.class)
@EnableMongoAuditing
@Configuration
public class MongoConfiguration {
	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions( List.of( new MoneyReadConverter(), new MoneyWriteConverter() ) );
	}
}