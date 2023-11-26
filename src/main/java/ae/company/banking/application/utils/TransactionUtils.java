package ae.company.banking.application.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TransactionUtils {

	private TransactionUtils() {
		throw new AssertionError( "This class could not be instanciated" );
	}

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int RANDOM_STRING_LENGTH = 7;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern( "yyyyMMddHHmmss" );
	private static final Random random = new Random();

	public static String generateReference() {
		String dateTimePart = LocalDateTime.now().format( DATE_TIME_FORMATTER );
		String randomStringPart = generateRandomString();

		return dateTimePart + randomStringPart;
	}

	private static String generateRandomString() {

		StringBuilder sb = new StringBuilder( RANDOM_STRING_LENGTH );

		for( int i = 0; i < RANDOM_STRING_LENGTH; i++ ){
			sb.append( CHARACTERS.charAt( random.nextInt( CHARACTERS.length() ) ) );
		}

		return sb.toString();
	}
}
