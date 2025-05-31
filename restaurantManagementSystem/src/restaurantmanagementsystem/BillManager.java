package restaurantmanagementsystem;

import java.io.*;
import java.util.*;

public class BillManager {
    private static final String FILE_NAME = "test/bills.txt";
    private Scanner input = new Scanner(System.in);
    public void AddBill() {
        try {
            System.out.print("Enter Bill ID: ");
            int billId = Integer.parseInt(input.nextLine());
            System.out.print("Enter Order ID: ");
            int orderId = Integer.parseInt(input.nextLine());
            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(input.nextLine());
            System.out.print("Enter Total Amount: ");
            double amount = Double.parseDouble(input.nextLine());
            Bill bill = new Bill(billId, orderId, customerId, amount);
            if (isBillIdExists(billId)) {
                System.out.println("⚠ Bill ID already exists. Please try with a different ID.");
                return;
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                bw.write(bill.toFileString());
                bw.newLine();
                System.out.println("Bill added successfully.");
            } catch (IOException e) {
                System.out.println("Error writing bill: " + e.getMessage());
                return;
            }
            
            CustomerManager customerManager = new CustomerManager();
            customerManager.addBillToCustomer(customerId, billId, amount);
            
            // Check for gifts based on total payments
            GiftManager.checkAndNotifyGift(customerId);

        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid input format. Please enter valid numbers.");
        }
    } private boolean isBillIdExists(int billId) {
        List<Bill> bills = readBillsFromFile();
        for (Bill b : bills) {
            if (b.getBillId() == billId) {
                return true;
            }
        }
        return false;
    } public List<Bill> readBillsFromFile() {
    List<Bill> bills = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            Bill bill = Bill.fromFileString(line);
            if (bill != null) {
                bills.add(bill);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading bills: " + e.getMessage());
    }
    return bills;
 } public void listBills() {
    List<Bill> bills = readBillsFromFile();

    if (bills.isEmpty()) {
        System.out.println("⚠ No bills to display.");
    } else {
        System.out.println("\n--- All Bills ---");
        for (Bill bill : bills) {
            System.out.println(bill); // يستخدم toString() من كلاس Bill
        }
    }
  } public void SearchBill() {
        System.out.print("Enter Bill ID to search: ");
        int id = Integer.parseInt(input.nextLine());

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                Bill bill = Bill.fromFileString(line);
                if (bill != null && bill.getBillId() == id) {
                    System.out.println("Bill found: " + bill);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Bill not found.");
            }
        } catch (IOException e) {
            System.out.println("Error searching bill: " + e.getMessage());
        }
    } public void UpdateBill() {

    System.out.print("Enter Bill ID to update: ");
    int billIdToUpdate = Integer.parseInt(input.nextLine());

    System.out.print("Enter Customer ID: ");
    int customerId = Integer.parseInt(input.nextLine());
    System.out.print("Enter new Amount: ");
    double newAmount = Double.parseDouble(input.nextLine());

    List<Bill> bills = readBillsFromFile();
    boolean found = false;

    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billIdToUpdate && bill.getCustomerId() == customerId) {
                writer.println(bill.getBillId() + "," + bill.getOrderId() + "," + bill.getCustomerId() + "," + newAmount);
                found = true;
            } else {
                writer.println(bill.toFileString());
            }
        }
    } catch (IOException e) {
        System.out.println("⚠ Error while updating bill: " + e.getMessage());
        return;
    }

    if (found) {
        System.out.println("Bill updated successfully.");
        GiftManager.checkAndNotifyGift(customerId);
    } else {
        System.out.println("No bill found matching the provided information.");
    }
 } public void DeleteBill() {
    

    System.out.print("Enter Bill ID to delete: ");
    int billIdToDelete = Integer.parseInt(input.nextLine());

    System.out.print("Enter Customer ID: ");
    int customerIdToMatch = Integer.parseInt(input.nextLine());

    List<Bill> bills = readBillsFromFile();
    List<Bill> updatedBills = new ArrayList<>();
    boolean found = false;

    for (Bill bill : bills) {
        if (!(bill.getBillId() == billIdToDelete && bill.getCustomerId() == customerIdToMatch)) {
            updatedBills.add(bill);
        } else {
            found = true;
        }
    }

    if (found) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Bill bill : updatedBills) {
                writer.println(bill.toFileString());
            }
            System.out.println("✅ Bill deleted successfully.");
        } catch (IOException e) {
            System.out.println("⚠ Error while deleting bill: " + e.getMessage());
        }
    } else {
        System.out.println("⚠ No bill found with the provided Bill ID and Customer ID.");
    }
}


   


    /*public void addBill() {
        System.out.print("Enter Bill ID: ");
        int billId = Integer.parseInt(input.nextLine());
        System.out.print("Enter Order ID: ");
        int orderId = Integer.parseInt(input.nextLine());
        System.out.print("Enter Customer ID: "); 
        int customerId = Integer.parseInt(input.nextLine());
        System.out.print("Enter Total Amount: ");
        double amount = Double.parseDouble(input.nextLine());
        GiftManager.checkAndNotifyGift(customerId);


        Bill bill = new Bill(billId, orderId, customerId, amount);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(bill.toFileString());
            bw.newLine();
            System.out.println("Bill added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing bill: " + e.getMessage());
        }


        GiftManager.checkAndNotifyGift(customerId);
    }


    public void listBills() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- All Bills ---");
            while ((line = br.readLine()) != null) {
                Bill bill = Bill.fromFileString(line);
                if (bill != null) {
                    System.out.println(bill);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading bills: " + e.getMessage());
        }
    }

    public void searchBill() {
        System.out.print("Enter Bill ID to search: ");
        int id = Integer.parseInt(input.nextLine());

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                Bill bill = Bill.fromFileString(line);
                if (bill != null && bill.getBillId() == id) {
                    System.out.println("Bill found: " + bill);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Bill not found.");
            }
        } catch (IOException e) {
            System.out.println("Error searching bill: " + e.getMessage());
        }
    }*/
}
