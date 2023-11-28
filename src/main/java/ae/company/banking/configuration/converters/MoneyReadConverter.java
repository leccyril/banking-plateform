package ae.company.banking.configuration.converters;

import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MoneyReadConverter implements Converter<String, MonetaryAmount> {
	@Override
	public MonetaryAmount convert(String moneyString) {
		return Money.parse(moneyString);
	}
}