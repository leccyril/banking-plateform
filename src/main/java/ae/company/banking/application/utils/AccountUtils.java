package ae.company.banking.application.utils;

import java.util.Random;

public class AccountUtils {

	private AccountUtils() {
		throw new AssertionError( "This class could not be instanciated" );
	}

	private static final String CHARACTERS = "0123456789";
	private static final int RANDOM_LENGTH = 12;
	private static final Random random = new Random();

	public static String generateIban() {
		String randomStringPart = generateRandomString();
		return "LU" + randomStringPart;
	}

	private static String generateRandomString() {
		StringBuilder sb = new StringBuilder( RANDOM_LENGTH );
		for( int i = 0; i < RANDOM_LENGTH; i++ ){
			sb.append( CHARACTERS.charAt( random.nextInt( CHARACTERS.length() ) ) );
		}
		return sb.toString();
	}
}
