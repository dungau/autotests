package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalVariables {

    public final static int SHORT_WAIT				=5;
    public final static int MEDIUM_WAIT				=15;
    public final static int LONG_WAIT				=30;
    public final static int SUPER_LONG_WAIT			=45;

    public final static String CHROMEDRIVER_PATH	="lib//chromedriver";

    public final static String URL					="https://protonmail.com";
    public final static String USERNAME				="auto_test_selenium@protonmail.com";
    public final static String PASSWORD				="Bobby123456789";
    public final static String EMAIL_SUBJECT        ="Test Email Subject " + GlobalVariables.CURRENT_LOCAL_DATETIME;
    public final static String EMAIL_TO             ="auto_test_selenium@protonmail.com";
    public final static String EMAIL_BODY           ="Test Email Body " + GlobalVariables.CURRENT_LOCAL_DATETIME;
    protected final static String CURRENT_LOCAL_DATETIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());

}
