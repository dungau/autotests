package entities;

import enums.UserTypes;
import lombok.*;
import utils.UserLoader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String ssn;

    private String type;

    // Declare DateTimeFormatter with desired format
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Save current LocalDateTime into a variable
    static LocalDateTime localDateTime = LocalDateTime.now();

    //Declare Date for startMovingDate
    static String startDate = localDateTime.format(dateTimeFormatter);

    // Format LocalDateTime into a String variable and print
    static DateTimeFormatter  dateTimeFormatterForRandom = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    static String formattedLocalDateTime = localDateTime.format(dateTimeFormatterForRandom);

    public static Users getUser(UserTypes userTypes){
        Users user = UserLoader.findUserByType(userTypes);
        user.setFirstName(String.format(user.getFirstName(), formattedLocalDateTime));
        user.setLastName(String.format(user.getLastName(), formattedLocalDateTime));
        user.setUsername(String.format(user.getUsername(), formattedLocalDateTime));
        return user;
    }

    public static Users getBlankData() {
        Users users = new Users();
        users.setUsername("");
        users.setPassword("");
        users.setFirstName("");
        users.setLastName("");
        users.setAddress("");
        users.setCity("");
        users.setState("");
        users.setZipCode("");
        users.setPhoneNumber("");
        users.setPassword("");
        return users;
    }

    public static Users getUpdatedUser(UserTypes userTypes) {
        String strUpdated = " updated";
        Users user = getUser(userTypes);
        user.firstName = user.firstName + strUpdated;
        user.lastName = user.lastName + strUpdated;
        user.address = user.address + strUpdated;
        user.city = user.city + strUpdated;
        user.state = user.state + strUpdated;
        return user;
    }

}
