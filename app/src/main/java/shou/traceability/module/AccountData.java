package shou.traceability.module;

public class AccountData {
    private String phoneNumber;
    private String password;

    public AccountData(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
