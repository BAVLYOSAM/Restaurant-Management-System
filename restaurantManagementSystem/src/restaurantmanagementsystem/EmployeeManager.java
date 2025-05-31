package restaurantmanagementsystem;
import java.io.*;
import java.util.*;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private Scanner input = new Scanner(System.in);

    public void addEmployee() {
        int id = -1;
        while (true) {
            System.out.print("Enter ID: ");
            if (input.hasNextInt()) {
                id = input.nextInt();
                input.nextLine();

                boolean exists = false;
                List<Employee> employees = getAllEmployees();
                for (Employee emp : employees) {
                    if (emp.getId() == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("This ID is already taken. Please enter a different ID.");
                } else {
                    break;  // ID صالح وغير موجود مسبقًا، نخرج من الحلقة
                }
            } else {
                System.out.println("Invalid ID. Please enter a valid number.");
                input.nextLine();
            }
        }

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter phone: ");
        String phone = input.nextLine();

        Employee emp = new Employee(id, name, phone);

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            pw.println(emp.toFileString());
            System.out.println("-------Employee added successfully--------.");
        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public void listEmployees() {
        List<Employee> list = getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    public void deleteEmployee() {
        System.out.print("Enter ID to delete: ");
        int id = input.nextInt();
        List<Employee> list = getAllEmployees();
        boolean found = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : list) {
                if (emp.getId() != id) {
                    pw.println(emp.toFileString());
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }

        if (found) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateEmployee() {
        System.out.print("Enter ID to update: ");
        int id = input.nextInt(); input.nextLine();

        List<Employee> list = getAllEmployees();
        boolean found = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : list) {
                if (emp.getId() == id) {
                    System.out.print("Enter new name: ");
                    String name = input.nextLine();
                    System.out.print("Enter new phone: ");
                    String phone = input.nextLine();

                    Employee updated = new Employee(id, name, phone);
                    pw.println(updated.toFileString());
                    found = true;
                } else {
                    pw.println(emp.toFileString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }

        if (found) {
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void searchEmployee() {
        System.out.print("Enter ID to search: ");
        int id = input.nextInt();
        List<Employee> list = getAllEmployees();

        for (Employee emp : list) {
            if (emp.getId() == id) {
                System.out.println("Employee found:");
                System.out.println(emp);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String phone = parts[2];
                    list.add(new Employee(id, name, phone));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
    public boolean login() {

        System.out.print("Enter your employee ID: ");
        String inputId = input.nextLine().trim();

        System.out.print("Enter your name: ");
        String inputName = input.nextLine().trim();



        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 3) {
                    String fileId = parts[0].trim();
                    String fileName = parts[1].trim();
                    String filePassword = parts[2].trim();

                    if (fileId.equalsIgnoreCase(inputId) &&
                        fileName.equalsIgnoreCase(inputName)
                       ) {
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("⚠ Error reading employees file: " + e.getMessage());
        }

        return false;
    }
}

