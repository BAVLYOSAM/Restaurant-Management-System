package restaurantmanagementsystem;

public class user {
    private String username;
    private String password;

    public user() {}

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toFileString() {
        return username + "," + password;
    }

    public static user fromFileString(String line) {
        String[] parts = line.split(",");
        if(parts.length == 2) {
            return new user(parts[0], parts[1]);
        }
        return null;
    }
}
