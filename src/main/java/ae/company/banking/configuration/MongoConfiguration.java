package ae.company.banking.configuration;


import ae.company.banking.configuration.converters.MoneyReadConverter;
import ae.company.banking.configuration.converters.MoneyWriteConverter;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

@Configuration
public class MongoConfiguration {
	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions( List.of( new MoneyReadConverter(), new MoneyWriteConverter() ) );
	}
}