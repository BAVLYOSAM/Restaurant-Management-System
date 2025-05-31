package restaurantmanagementsystem;

public class Bill {
    private int billId;
    private int orderId;
    private int customerId; 
    private double amount;

    public Bill(int billId, int orderId, int customerId, double amount) {
        this.billId = billId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getBillId() {
        return billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String toFileString() {
        return billId + "," + orderId + "," + customerId + "," + amount;
    }

    public static Bill fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            int billId = Integer.parseInt(parts[0]);
            int orderId = Integer.parseInt(parts[1]);
            int customerId = Integer.parseInt(parts[2]);
            double amount = Double.parseDouble(parts[3]);
            return new Bill(billId, orderId, customerId, amount);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId + " | Order ID: " + orderId + " | Customer ID: " + customerId + " | Amount: " + amount;
    }
}
