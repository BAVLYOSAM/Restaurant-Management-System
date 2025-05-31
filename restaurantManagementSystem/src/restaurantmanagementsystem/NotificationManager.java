package restaurantmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private static List<String> notifications = new ArrayList<>();

    public static void addNotification(String message) {
        notifications.add(message);
        System.out.println("Notification added: " + message);
    }

    public static void showNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            System.out.println("---- Notifications ----");
            for (String n : notifications) {
                System.out.println("- " + n);
            }
            System.out.println("-----------------------");
            notifications.clear();
        }
    }
}
