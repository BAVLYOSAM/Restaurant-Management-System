package restaurantmanagementsystem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AdminReport {

    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";

    public void generateEmployeeReport() {
        System.out.println("\nemployee Report:");
        try {
            List<String> lines = Files.readAllLines(Paths.get(EMPLOYEES_FILE));
            if (lines.isEmpty()) {
                System.out.println("No employee data found.");
                return;
            }

            // عدلنا الهيدر ليطابق البيانات اللي عندك (id, name, phone)
            System.out.printf("%-5s %-15s %-15s\n", "id", "name", "phone");
            System.out.println("--------------------------------------------");

            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    System.out.printf("%-5s %-15s %-15s\n", data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("error reading employee report.");
        }
    }


    public void generateCustomerReport() {
        System.out.println("\ncustomer Report:");
        try {
            List<String> lines = Files.readAllLines(Paths.get(CUSTOMERS_FILE));
            if (lines.isEmpty()) {
                System.out.println("no customer data found.");
                return;
            }

            System.out.printf("%-5s %-15s %-15s\n", "id", "name", "phone");
            System.out.println("------------------------------------------");

            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    System.out.printf("%-5s %-15s %-15s\n", data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println(".----------------------------------.");
            System.out.println("error reading customer report.");
            System.out.println(".----------------------------------.");
        }
    }

    public void showAllReports() {
        generateEmployeeReport();
        generateCustomerReport();
    }


}
