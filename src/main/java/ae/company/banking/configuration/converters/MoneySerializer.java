package ae.company.banking.configuration.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.javamoney.moneta.Money;

public class MoneySerializer extends JsonSerializer<Money> {
	@Override
	public void serialize(Money value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("amount", value.getNumber().doubleValue());
		gen.writeStringField("currency", value.getCurrency().getCurrencyCode());
		gen.writeEndObject();
	}
}
