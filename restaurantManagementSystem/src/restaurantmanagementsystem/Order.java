package restaurantmanagementsystem;

public class Order {
    private int orderId;
    private int customerId;
    private String details;
    private String status;

    public Order(int orderId, int customerId, String details, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.details = details;
        this.status = status;
    }

    public String toFileString() {
        return orderId + "," + customerId + "," + details + "," + status;
    }

    public static Order fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            int orderId = Integer.parseInt(parts[0]);
            int customerId = Integer.parseInt(parts[1]);
            return new Order(orderId, customerId, parts[2], parts[3]);
        }
        return null;
    }

    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
