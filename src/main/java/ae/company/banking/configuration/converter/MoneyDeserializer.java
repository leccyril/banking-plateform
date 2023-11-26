package ae.company.banking.configuration.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;

public class MoneyDeserializer extends JsonDeserializer<Money> {

	@Override
	public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		JsonNode node = p.getCodec().readTree(p);
		double amount = node.get("amount").asDouble();
		String currencyCode = node.get("currency").asText();

		CurrencyUnit currency = Monetary.getCurrency(currencyCode);
		return Money.of(amount, currency);
	}
}