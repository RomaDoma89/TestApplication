package testapp.zahorui.com.testapplication;

/**
 * Created by Roman Zahorui on 29.11.2016.
 */

public class UserObj {
    private String firstName;
    private String lastName;

    public UserObj(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
