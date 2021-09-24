package enums;

public enum OsTypes {
    WIN, LINUX, MAC;

    public static OsTypes getOs() {
        String osName = System.getProperty("os.name").toUpperCase();
        for(OsTypes os:values()) {
            if (osName.contains(os.toString())) {
                return os;
            }
        }
        return LINUX;
    }
}