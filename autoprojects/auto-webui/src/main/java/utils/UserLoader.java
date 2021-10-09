package utils;

import entities.Users;
import enums.UserTypes;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserLoader {
    private static List<Users> USER_POOL;

    private static String getUserCsvPath() {
        String env = System.getProperty("environment");
        return String.format("./src/test/resources/%s-users.csv", env != null ? env : "test");
    }

    private static List<Users> readAllRows(String userCsvPath) {
        List<Users> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(userCsvPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 11);
                records.add(createdUserData(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private static Users createdUserData(String[] strData) {
        Users user = new Users();
        user.setType(strData[0]);
        user.setFirstName(strData[1]);
        user.setLastName(strData[2]);
        user.setAddress(strData[3]);
        user.setCity(strData[4]);
        user.setState(strData[5]);
        user.setZipCode(strData[6]);
        user.setPhoneNumber(strData[7]);
        user.setSsn(strData[8]);
        user.setUsername(strData[9]);
        user.setPassword(strData[10]);
        return user;
    }

    public static Users findUserByType(UserTypes userTypes) {
        USER_POOL = readAllRows(getUserCsvPath());
        Users userByType = new Users();
        for (Users user : USER_POOL) {
            if (user.getType().equals(userTypes.name())) {
                userByType = user;
            }
        }
        return userByType;
    }

}
