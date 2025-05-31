package restaurantmanagementsystem;

public class Admin extends user {
    public Admin() {
        super();
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    public String toFileString() {
        return getUsername() + "," + getPassword();
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new Admin(parts[0], parts[1]);
        }
        return null;
    }
}