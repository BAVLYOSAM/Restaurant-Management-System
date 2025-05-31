/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementsystem;

public class Offer {
    private String type;    // special, marketing, loyalty, reward
    private String description;
    private double discountPercentage;

    public Offer(String type, String description, double discountPercentage) {
        this.type = type;
        this.description = description;
        this.discountPercentage = discountPercentage;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String toFileString() {
        return type + "," + description + "," + discountPercentage;
    }

    public void printOffer() {
        System.out.println("Type: " + type);
        System.out.println("Description: " + description);
        System.out.println("Discount: " + discountPercentage + "%");
    }
}