/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementsystem;

import java.io.*;
import java.util.*;

public class OfferManager {

    // Add Offer
    public static void addOffer() {
        Scanner sc = new Scanner(System.in);

        // ======================== notification offer ========================
        System.out.print("Enter offer description: ");
        String description = sc.nextLine();
        NotificationManager.addNotification("New offer added: " + description);
        // ====================================================================


        System.out.print("Enter offer type (special, marketing, loyalty, reward): ");
        String type = sc.nextLine().toLowerCase();
        if (!Arrays.asList("special", "marketing", "loyalty", "reward").contains(type)) {
            System.out.println("Invalid offer type.");
            return;
        }

        double discount = -1;
        while (true) {
            System.out.print("Enter discount percentage (0-100): ");
            if (sc.hasNextDouble()) {
                discount = sc.nextDouble();
                sc.nextLine();
                if (discount >= 0 && discount <= 100) {
                    break;
                } else {
                    System.out.println("Discount must be between 0 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }

        Offer newOffer = new Offer(type, description, discount);

        try (FileWriter fw = new FileWriter("offers.txt", true)) {
            fw.write(newOffer.toFileString() + "\n");
            System.out.println("Offer added and saved.");
        } catch (IOException e) {
            System.out.println("Error saving offer.");
        }
    }

    // List Offers
    public static void listOffers() {
        List<Offer> offers = readOffersFromFile();

        if (offers.isEmpty()) {
            System.out.println("No offers found.");
            return;
        }

        for (Offer o : offers) {
            o.printOffer();
            System.out.println("------------------");
        }
    }

    // Search Offer
    public static void searchOffer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter offer type to search: ");
        String type = sc.nextLine().toLowerCase();

        List<Offer> offers = readOffersFromFile();
        boolean found = false;

        for (Offer o : offers) {
            if (o.getType().equalsIgnoreCase(type)) {
                o.printOffer();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Offer not found.");
        }
    }

    // Delete Offer
    public static void deleteOffer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter offer type to delete: ");
        String type = sc.nextLine().toLowerCase();

        List<Offer> offers = readOffersFromFile();
        boolean found = false;

        Iterator<Offer> it = offers.iterator();
        while (it.hasNext()) {
            Offer o = it.next();
            if (o.getType().equalsIgnoreCase(type)) {
                it.remove();
                found = true;
                break;
            }
        }

        if (found) {
            writeOffersToFile(offers);
            System.out.println("Offer deleted.");
        } else {
            System.out.println("Offer not found.");
        }
    }

    // Update Offer
    public static void updateOffer() {
        List<Offer> offers = readOffersFromFile();

        if (offers.isEmpty()) {
            System.out.println("The file is empty. Please add offers first.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter offer type to update: ");
        String type = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Offer o : offers) {
            if (o.getType().equalsIgnoreCase(type)) {
                System.out.print("Enter new description: ");
                String newDesc = sc.nextLine();
                double newDiscount = -1;

                while (true) {
                    System.out.print("Enter new discount (0-100): ");
                    if (sc.hasNextDouble()) {
                        newDiscount = sc.nextDouble();
                        sc.nextLine();
                        if (newDiscount >= 0 && newDiscount <= 100) {
                            break;
                        } else {
                            System.out.println("Discount must be between 0 and 100.");
                        }
                    } else {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                    }
                }

                o.setDescription(newDesc);
                o.setDiscountPercentage(newDiscount);
                found = true;
                break;
            }
        }

        if (found) {
            writeOffersToFile(offers);
            System.out.println("Offer updated.");
        } else {
            System.out.println("Offer not found.");
        }
    }

    // Assign Offer to Customer
    public static void assignOfferToCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID to assign offer: ");
        int customerId = Integer.parseInt(sc.nextLine());
        
        CustomerManager customerManager = new CustomerManager();
        Customer customer = customerManager.getCustomerById(customerId);
        
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        List<Offer> offers = readOffersFromFile();
        if (offers.isEmpty()) {
            System.out.println("No offers available.");
            return;
        }
        
        System.out.println("\n--- Available Offers ---");
        for (int i = 0; i < offers.size(); i++) {
            System.out.println((i + 1) + ". " + offers.get(i).getType() + " - " + offers.get(i).getDescription());
        }
        
        System.out.print("Select offer to assign (1-" + offers.size() + "): ");
        int offerIndex = Integer.parseInt(sc.nextLine()) - 1;
        
        if (offerIndex >= 0 && offerIndex < offers.size()) {
            Offer selectedOffer = offers.get(offerIndex);
            customerManager.addOfferToCustomer(customerId, selectedOffer.getDescription());
            System.out.println("Offer \"" + selectedOffer.getDescription() + "\" assigned to customer #" + customerId);
            
            // Add notification
            NotificationManager.addNotification("Offer assigned to customer #" + customerId + ": " + selectedOffer.getDescription());
        } else {
            System.out.println("Invalid offer selection.");
        }
    }

    // ======================== Helper Methods ========================

    // Read Offers from File
    private static List<Offer> readOffersFromFile() {
        List<Offer> offers = new ArrayList<>();
        File file = new File("offers.txt");
        if (!file.exists()) return offers;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0].trim();
                    String desc = parts[1].trim();
                    double discount = Double.parseDouble(parts[2].trim());
                    offers.add(new Offer(type, desc, discount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading offers.txt");
        }

        return offers;
    }

    // Write Offers to File
    private static void writeOffersToFile(List<Offer> offers) {
        try (FileWriter fw = new FileWriter("offers.txt", false)) {
            for (Offer o : offers) {
                fw.write(o.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to offers.txt");
        }
    }

}