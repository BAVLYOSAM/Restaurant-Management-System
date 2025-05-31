package restaurantmanagementsystem;

import java.io.*;

public class GiftManager {
    private static final String BILL_FILE = "test/bills.txt";
    private static final String NOTIFICATION_FILE = "notifications.txt";

    private static final double GIFT_THRESHOLD = 500.0;

    public static void checkAndNotifyGift(int customerId) {
        double totalPayments = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(BILL_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int billCustomerId = Integer.parseInt(parts[2]);
                    double amount = Double.parseDouble(parts[3]);
                    if (billCustomerId == customerId) {
                        totalPayments += amount;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading bills for gift check: " + e.getMessage());
        }

        if (totalPayments >= GIFT_THRESHOLD) {
            String giftMessage = "Complimentary Dessert for reaching " + GIFT_THRESHOLD + " in total payments";
            
            // Add gift to the customer profile
            CustomerManager customerManager = new CustomerManager();
            customerManager.addGiftToCustomer(customerId, giftMessage);
            
            String notification = "Customer ID " + customerId + " has reached total payments of " + totalPayments + " and earned a gift!";
            addNotification(notification);
            System.out.println("Notification added: " + notification);
        }
    }
    private static void addNotification(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOTIFICATION_FILE, true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing notification: " + e.getMessage());
        }
    }
}
