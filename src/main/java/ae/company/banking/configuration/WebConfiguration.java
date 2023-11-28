package ae.company.banking.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

//Not yet activated
@Configuration
@Profile("cors")
public class WebConfiguration {

	@Bean
	CorsWebFilter corsWebFilter() {
		var corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins( List.of("localhost", "mytrustedwebsite.com"));
		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsWebFilter(source);
	}
}
