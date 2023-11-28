package ae.company.banking.configuration.converters;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.javamoney.moneta.Money;

public class MoneyModule extends SimpleModule {
	public MoneyModule() {
		addSerializer( Money.class, new MoneySerializer());
		addDeserializer( Money.class, new MoneyDeserializer());
	}
}
