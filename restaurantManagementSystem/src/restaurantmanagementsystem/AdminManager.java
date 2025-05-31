package restaurantmanagementsystem;
import java.io.*;
import java.util.*;

public class AdminManager {
    private static final String FILE_NAME = "admins.txt";
    private Scanner input = new Scanner(System.in);

    public boolean login() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        List<Admin> admins = getAllAdmins();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void updateAdmin() {
        System.out.print("Enter admin username to update: ");
        String username = input.nextLine();

        List<Admin> admins = getAllAdmins();
        boolean found = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username)) {
                    System.out.print("Enter new username: ");
                    String newUsername = input.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();

                    Admin updatedAdmin = new Admin(newUsername, newPassword);
                    pw.println(updatedAdmin.toFileString());
                    found = true;
                } else {
                    pw.println(admin.toFileString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating admin: " + e.getMessage());
        }

        if (found) {
            System.out.println("Admin updated successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    private List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Admin admin = Admin.fromFileString(line);
                if (admin != null) {
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading admin file: " + e.getMessage());
        }
        return admins;
    }
}
