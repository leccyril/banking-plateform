package ae.company.banking.configuration;


import ae.company.banking.configuration.converter.MoneyReadConverter;
import ae.company.banking.configuration.converter.MoneyWriteConverter;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfiguration {
	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions( List.of( new MoneyReadConverter(), new MoneyWriteConverter() ) );
	}
}