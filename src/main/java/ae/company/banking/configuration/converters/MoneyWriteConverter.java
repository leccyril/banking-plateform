package ae.company.banking.configuration.converters;

import javax.money.MonetaryAmount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class MoneyWriteConverter implements Converter<MonetaryAmount, String> {
	@Override
	public String convert(MonetaryAmount money) {
		return money.toString();
	}
}