package ae.company.banking.application.mapper;

import ae.company.banking.domain.user.entities.User;
import ae.company.banking.infrastructure.dto.UserDto;

public class UserMapper {

	private UserMapper() {
		throw new AssertionError( "Could not be instantiate" );
	}

	public static UserDto mapToUserDto(User user) {
		return UserDto.builder()
				.firstName( user.getFirstName() )
				.lastName( user.getLastName() )
				.address( user.getAddress() )
				.accounts( user.getPersonalAccounts() )
				.phoneNumber( user.getPhoneNumber() )
				.build();
	}

	public static User mapToUser(UserDto userDto) {
		return User.builder()
				.firstName( userDto.getFirstName() )
				.lastName( userDto.getLastName() )
				.phoneNumber( userDto.getPhoneNumber() )
				.address( userDto.getAddress() )
				.accounts( userDto.getAccounts() )
				.accounts( userDto.getAccounts() )
				.build();
	}
}
