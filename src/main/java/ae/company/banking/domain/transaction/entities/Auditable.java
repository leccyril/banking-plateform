package ae.company.banking.domain.transaction.entities;

import java.time.Instant;
import java.time.LocalDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

public class Auditable {

	@CreatedDate
	private Instant creationDate;
	@LastModifiedDate
	private Instant updateDate;
}
