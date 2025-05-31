package restaurantmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private double totalPayments;
    private boolean marketingProgram;
    private boolean loyaltyProgram;
    private boolean rewardProgram;
    private List<Integer> orderIds;
    private List<Integer> billIds;
    private List<String> specialOffers;
    private List<String> gifts;

    public Customer() {
        this.orderIds = new ArrayList<>();
        this.billIds = new ArrayList<>();
        this.specialOffers = new ArrayList<>();
        this.gifts = new ArrayList<>();
    }

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.totalPayments = 0;
        this.marketingProgram = false;
        this.loyaltyProgram = false;
        this.rewardProgram = false;
        this.orderIds = new ArrayList<>();
        this.billIds = new ArrayList<>();
        this.specialOffers = new ArrayList<>();
        this.gifts = new ArrayList<>();
    }

    public Customer(int id, String name, String phone, double totalPayments) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.totalPayments = totalPayments;
        this.marketingProgram = false;
        this.loyaltyProgram = false;
        this.rewardProgram = false;
        this.orderIds = new ArrayList<>();
        this.billIds = new ArrayList<>();
        this.specialOffers = new ArrayList<>();
        this.gifts = new ArrayList<>();
    }

    public Customer(int id, String name, String phone, double totalPayments, 
                   boolean marketingProgram, boolean loyaltyProgram, boolean rewardProgram) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.totalPayments = totalPayments;
        this.marketingProgram = marketingProgram;
        this.loyaltyProgram = loyaltyProgram;
        this.rewardProgram = rewardProgram;
        this.orderIds = new ArrayList<>();
        this.billIds = new ArrayList<>();
        this.specialOffers = new ArrayList<>();
        this.gifts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    public boolean isInMarketingProgram() {
        return marketingProgram;
    }

    public boolean isInLoyaltyProgram() {
        return loyaltyProgram;
    }

    public boolean isInRewardProgram() {
        return rewardProgram;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public List<Integer> getBillIds() {
        return billIds;
    }

    public List<String> getSpecialOffers() {
        return specialOffers;
    }

    public List<String> getGifts() {
        return gifts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTotalPayments(double totalPayments) {
        this.totalPayments = totalPayments;
    }

    public void setMarketingProgram(boolean marketingProgram) {
        this.marketingProgram = marketingProgram;
    }

    public void setLoyaltyProgram(boolean loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public void setRewardProgram(boolean rewardProgram) {
        this.rewardProgram = rewardProgram;
    }

    public void addOrderId(int orderId) {
        if (!orderIds.contains(orderId)) {
            orderIds.add(orderId);
        }
    }

    public void addBillId(int billId) {
        if (!billIds.contains(billId)) {
            billIds.add(billId);
        }
    }

    public void addSpecialOffer(String offer) {
        specialOffers.add(offer);
    }

    public void addGift(String gift) {
        gifts.add(gift);
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",").append(name).append(",").append(phone).append(",").append(totalPayments);
        sb.append(",").append(marketingProgram).append(",").append(loyaltyProgram).append(",").append(rewardProgram);
        
        sb.append(",");
        if (!orderIds.isEmpty()) {
            for (int i = 0; i < orderIds.size(); i++) {
                sb.append(orderIds.get(i));
                if (i < orderIds.size() - 1) {
                    sb.append(";");
                }
            }
        }
        
        sb.append(",");
        if (!billIds.isEmpty()) {
            for (int i = 0; i < billIds.size(); i++) {
                sb.append(billIds.get(i));
                if (i < billIds.size() - 1) {
                    sb.append(";");
                }
            }
        }
        
        sb.append(",");
        if (!specialOffers.isEmpty()) {
            for (int i = 0; i < specialOffers.size(); i++) {
                sb.append(specialOffers.get(i));
                if (i < specialOffers.size() - 1) {
                    sb.append(";");
                }
            }
        }
        
        sb.append(",");
        if (!gifts.isEmpty()) {
            for (int i = 0; i < gifts.size(); i++) {
                sb.append(gifts.get(i));
                if (i < gifts.size() - 1) {
                    sb.append(";");
                }
            }
        }
        
        return sb.toString();
    }

    public static Customer fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length >= 4) {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String phone = parts[2];
            double totalPayments = Double.parseDouble(parts[3]);
            
            Customer customer = new Customer(id, name, phone, totalPayments);
            
            if (parts.length >= 7) {
                customer.setMarketingProgram(Boolean.parseBoolean(parts[4]));
                customer.setLoyaltyProgram(Boolean.parseBoolean(parts[5]));
                customer.setRewardProgram(Boolean.parseBoolean(parts[6]));
                
                if (parts.length >= 8 && !parts[7].isEmpty()) {
                    String[] orderIdParts = parts[7].split(";");
                    for (String orderIdStr : orderIdParts) {
                        if (!orderIdStr.isEmpty()) {
                            customer.addOrderId(Integer.parseInt(orderIdStr));
                        }
                    }
                }
                
                if (parts.length >= 9 && !parts[8].isEmpty()) {
                    String[] billIdParts = parts[8].split(";");
                    for (String billIdStr : billIdParts) {
                        if (!billIdStr.isEmpty()) {
                            customer.addBillId(Integer.parseInt(billIdStr));
                        }
                    }
                }
                
                if (parts.length >= 10 && !parts[9].isEmpty()) {
                    String[] offerParts = parts[9].split(";");
                    for (String offer : offerParts) {
                        if (!offer.isEmpty()) {
                            customer.addSpecialOffer(offer);
                        }
                    }
                }
                
                if (parts.length >= 11 && !parts[10].isEmpty()) {
                    String[] giftParts = parts[10].split(";");
                    for (String gift : giftParts) {
                        if (!gift.isEmpty()) {
                            customer.addGift(gift);
                        }
                    }
                }
            }
            
            return customer;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
          .append(" | Name: ").append(name)
          .append(" | Phone: ").append(phone)
          .append(" | Total Payments: ").append(totalPayments);
        
        sb.append("\nPrograms: ");
        if (marketingProgram) sb.append("Marketing ");
        if (loyaltyProgram) sb.append("Loyalty ");
        if (rewardProgram) sb.append("Reward ");
        if (!marketingProgram && !loyaltyProgram && !rewardProgram) sb.append("None");
        
        if (!orderIds.isEmpty()) {
            sb.append("\nOrders: ").append(orderIds);
        }
        
        if (!billIds.isEmpty()) {
            sb.append("\nBills: ").append(billIds);
        }
        
        if (!specialOffers.isEmpty()) {
            sb.append("\nSpecial Offers: ").append(specialOffers);
        }
        
        if (!gifts.isEmpty()) {
            sb.append("\nGifts: ").append(gifts);
        }
        
        return sb.toString();
    }
}
