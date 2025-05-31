package restaurantmanagementsystem;

import java.io.*;
import java.util.*;

public class CustomerManager {
    private static final String FILE_NAME = "customers.txt";
    private Scanner sc = new Scanner(System.in);

    public void addCustomer() {
        int id;
        while (true) {
            System.out.print("Enter ID: ");
            String idInput = sc.nextLine();
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a valid number.");
                continue;
            }

            if (getCustomerById(id) != null) {
                System.out.println("This ID already exists. Please enter a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        Customer customer = new Customer(id, name, phone);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(customer.toFileString());
            bw.newLine();
            System.out.println("Customer added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }


    public void listCustomers() {
        List<Customer> customers = getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer c : customers) {
                System.out.println(c);
                System.out.println("------------------------------------");
            }
        }
    }

    public void deleteCustomer() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        List<Customer> customers = getAllCustomers();
        boolean found = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                if (c.getId() != id) {
                    pw.println(c.toFileString());
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }

        if (found) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void updateCustomer() {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        List<Customer> customers = getAllCustomers();
        boolean found = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                if (c.getId() == id) {
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new phone: ");
                    String phone = sc.nextLine();
                    c.setName(name);
                    c.setPhone(phone);
                    found = true;
                }
                pw.println(c.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }

        if (found) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void searchCustomer() {
        System.out.print("Enter ID to search: ");
        int id = Integer.parseInt(sc.nextLine());

        List<Customer> customers = getAllCustomers();
        for (Customer c : customers) {
            if (c.getId() == id) {
                System.out.println("Customer found: " + c);
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Customer customer = Customer.fromFileString(line);
                if (customer != null) {
                    list.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customers: " + e.getMessage());
        }
        return list;
    }
    
    public boolean login() {
        System.out.print("Enter your name: ");
        String inputName = sc.nextLine().trim();

        System.out.print("Enter your customer ID: ");
        String inputId = sc.nextLine().trim();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();

                    if (name.equalsIgnoreCase(inputName) && id.equalsIgnoreCase(inputId)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("⚠ Error reading customers file: " + e.getMessage());
        }
        return false;
    }
    
    // Get customer by ID
    public Customer getCustomerById(int customerId) {
        List<Customer> customers = getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    // Add order to customer profile
    public void addOrderToCustomer(int customerId, int orderId) {
        updateCustomerProfile(customerId, customer -> {
            customer.addOrderId(orderId);
            System.out.println("Order #" + orderId + " added to customer #" + customerId + " profile.");
            return true;
        });
    }
    
    // Add bill to customer profile
    public void addBillToCustomer(int customerId, int billId, double amount) {
        updateCustomerProfile(customerId, customer -> {
            customer.addBillId(billId);
            customer.setTotalPayments(customer.getTotalPayments() + amount);
            System.out.println("Bill #" + billId + " added to customer #" + customerId + " profile.");
            return true;
        });
    }
    
    // Add gift to customer profile
    public void addGiftToCustomer(int customerId, String gift) {
        updateCustomerProfile(customerId, customer -> {
            customer.addGift(gift);
            System.out.println("Gift \"" + gift + "\" added to customer #" + customerId + " profile.");
            return true;
        });
    }
    
    // Add special offer to customer profile
    public void addOfferToCustomer(int customerId, String offer) {
        updateCustomerProfile(customerId, customer -> {
            customer.addSpecialOffer(offer);
            System.out.println("Offer \"" + offer + "\" added to customer #" + customerId + " profile.");
            return true;
        });
    }
    
    // Manage program registration
    public void manageCustomerPrograms() {
        System.out.print("Enter customer ID: ");
        int id = Integer.parseInt(sc.nextLine());
        
        Customer customer = getCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        System.out.println("\n--- Program Registration for " + customer.getName() + " ---");
        System.out.println("1. Marketing Program (" + (customer.isInMarketingProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("2. Loyalty Program (" + (customer.isInLoyaltyProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("3. Reward Program (" + (customer.isInRewardProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("4. Back");
        
        System.out.print("Select program to toggle enrollment: ");
        int choice = Integer.parseInt(sc.nextLine());
        
        switch (choice) {
            case 1 -> {
                updateCustomerProfile(id, customer1 -> {
                    customer1.setMarketingProgram(!customer1.isInMarketingProgram());
                    System.out.println("Marketing program enrollment " + 
                                      (customer1.isInMarketingProgram() ? "activated" : "deactivated") + 
                                      " for " + customer1.getName());
                    return true;
                });
            }
            case 2 -> {
                updateCustomerProfile(id, customer1 -> {
                    customer1.setLoyaltyProgram(!customer1.isInLoyaltyProgram());
                    System.out.println("Loyalty program enrollment " + 
                                      (customer1.isInLoyaltyProgram() ? "activated" : "deactivated") + 
                                      " for " + customer1.getName());
                    return true;
                });
            }
            case 3 -> {
                updateCustomerProfile(id, customer1 -> {
                    customer1.setRewardProgram(!customer1.isInRewardProgram());
                    System.out.println("Reward program enrollment " + 
                                      (customer1.isInRewardProgram() ? "activated" : "deactivated") + 
                                      " for " + customer1.getName());
                    return true;
                });
            }
            case 4 -> {
                return;
            }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    // View customer profile
    public void viewCustomerProfile() {
        System.out.print("Enter customer ID: ");
        int id = Integer.parseInt(sc.nextLine());
        
        Customer customer = getCustomerById(id);
        if (customer != null) {
            System.out.println("\n=== Customer Profile ===");
            System.out.println(customer);
            System.out.println("========================");
        } else {
            System.out.println("Customer not found.");
        }
    }
    
    // Customer self-service for program registration
    public void selfServiceProgramRegistration(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        System.out.println("\n--- Program Registration ---");
        System.out.println("1. Marketing Program (" + (customer.isInMarketingProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("2. Loyalty Program (" + (customer.isInLoyaltyProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("3. Reward Program (" + (customer.isInRewardProgram() ? "Enrolled" : "Not Enrolled") + ")");
        System.out.println("4. Back");
        
        System.out.print("Select program to toggle enrollment: ");
        int choice = Integer.parseInt(sc.nextLine());
        
        switch (choice) {
            case 1 -> {
                updateCustomerProfile(customerId, customer1 -> {
                    customer1.setMarketingProgram(!customer1.isInMarketingProgram());
                    System.out.println("Marketing program enrollment " + 
                                      (customer1.isInMarketingProgram() ? "activated" : "deactivated"));
                    return true;
                });
            }
            case 2 -> {
                updateCustomerProfile(customerId, customer1 -> {
                    customer1.setLoyaltyProgram(!customer1.isInLoyaltyProgram());
                    System.out.println("Loyalty program enrollment " + 
                                      (customer1.isInLoyaltyProgram() ? "activated" : "deactivated"));
                    return true;
                });
            }
            case 3 -> {
                updateCustomerProfile(customerId, customer1 -> {
                    customer1.setRewardProgram(!customer1.isInRewardProgram());
                    System.out.println("Reward program enrollment " + 
                                      (customer1.isInRewardProgram() ? "activated" : "deactivated"));
                    return true;
                });
            }
            case 4 -> {
                return;
            }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    // Customer views their own profile
    public void viewSelfProfile(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            System.out.println("\n=== Your Profile ===");
            System.out.println(customer);
            System.out.println("===================");
        } else {
            System.out.println("Profile not found.");
        }
    }
    
    // Update customer profile with a function
    private boolean updateCustomerProfile(int customerId, CustomerProfileUpdater updater) {
        List<Customer> customers = getAllCustomers();
        boolean found = false;
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                if (c.getId() == customerId) {
                    found = updater.update(c);
                }
                pw.println(c.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error updating customer profile: " + e.getMessage());
            return false;
        }
        
        return found;
    }
    
    // Functional interface for customer profile updates
    private interface CustomerProfileUpdater {
        boolean update(Customer customer);
    }
}


