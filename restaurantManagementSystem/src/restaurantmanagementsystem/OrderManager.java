package restaurantmanagementsystem;

import java.io.*;
import java.util.*;

public class OrderManager {
    private static final String ORDER_FILE = "orders.txt";
    private Scanner input = new Scanner(System.in);

    public void makeOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Order ID: ");
        int orderId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Order Details: ");
        String details = sc.nextLine();
        
        Order order = new Order(orderId, customerId, details, "Pending");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            bw.write(order.toFileString());
            bw.newLine();
            System.out.println("Order created successfully.");
            
            // Add order to customer profile
            CustomerManager customerManager = new CustomerManager();
            customerManager.addOrderToCustomer(customerId, orderId);
        } catch (IOException e) {
            System.out.println("Error writing order: " + e.getMessage());
        }
    }

    public void cancelOrder() {
        System.out.print("Enter Order ID to cancel: ");
        int orderId = input.nextInt();
        input.nextLine();

        List<Order> orders = getAllOrders();
        boolean found = false;

        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDER_FILE))) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    order.setStatus("cancelled");
                    found = true;
                }
                writer.println(order.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error updating order: " + e.getMessage());
        }

        if (found) {
            System.out.println("Order cancelled successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = Order.fromFileString(line);
                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading orders: " + e.getMessage());
        }
        return orders;
    }
}
