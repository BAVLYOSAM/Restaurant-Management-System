package restaurantmanagementsystem;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\n===== Restaurant Management System =====");

      boolean exitProgram = false;

       while (!exitProgram) {
          System.out.println("\nLogin as:");
          System.out.println("1. Admin");
          System.out.println("2. Employee");
          System.out.println("3. Customer");
          System.out.println("4. Exit");
          int userType = getUserChoice(1, 4);

         switch (userType) {
             case 1 -> {
                 AdminManager adminManager = new AdminManager();
                 if (!adminManager.login()) {
                      System.out.println("Incorrect admin login. Returning to main menu.");
                     continue;
                   }
                 System.out.println("Admin login successful!");
                 adminMenu(adminManager);
                }

              case 2 -> {
                   EmployeeManager employeeManager = new EmployeeManager();
                   if (!employeeManager.login()) {
                       System.out.println("Incorrect employee login. Returning to main menu.");
                      continue;
                    }
                  System.out.println("Employee login successful!");

                 CustomerManager customerManager = new CustomerManager();
                 OrderManager orderManager = new OrderManager();
                 BillManager billManager = new BillManager();

                  employeeMenu(employeeManager, customerManager, orderManager, billManager);
                }

             case 3 -> {
                   CustomerManager customerManager = new CustomerManager();
                   if (!customerManager.login()) {
                     System.out.println("Incorrect customer login. Returning to main menu.");
                     continue;
                    }
                    System.out.println("Customer login successful!");
                   // Get customer ID from input
                   System.out.print("Please re-enter your ID to access your profile: ");
                   int customerId = Integer.parseInt(sc.nextLine());
                   customerMenu(customerManager, customerId);
                }
             case 4 -> {
                 System.out.println("Exiting system. Goodbye!");
                 exitProgram = true;
                }
            }}
        }




    private static void adminMenu(AdminManager adminManager) {
    boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("1. Manage Employees");
            System.out.println("------------------------------------");
            System.out.println("2. manage meals" );
            System.out.println("------------------------------------");
            System.out.println("3. Manage Offers");
            System.out.println("------------------------------------");
            System.out.println("4. View Reports");
            System.out.println("------------------------------------");
            System.out.println("5. Update Admin");
            System.out.println("------------------------------------");
            System.out.println("6. Logout");
            System.out.println("------------------------------------");
            System.out.println("7. Exit");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 7);
            EmployeeManager employeeManager = new EmployeeManager();
            switch (choice) {
                case 1 -> manageEmployeesSubMenu(employeeManager);
                case 2 -> manageMealsMenu();
                case 3 -> manageOffers();
                case 4 -> {
                    AdminReport report = new AdminReport();
                    report.showAllReports();
                }
                case 5 -> adminManager.updateAdmin();
                case 6 -> {
                    System.out.println("Logged out successfully.");
                    return;
                }
                case 7 -> {
                    System.out.println("Exiting system. Goodbye!");
                    exit = true;
                }
            }
        }
    }
    private static void manageMealsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Meals ---");
            System.out.println("1. Add Meal");
            System.out.println("2. Update Meal");
            System.out.println("3. Delete Meal");
            System.out.println("4. List Meals");
            System.out.println("5. Back");

            int choice = getUserChoice(1, 5);

            switch (choice) {
                case 1 -> Meal.addMeal();
                case 2 -> Meal.updateMeal();
                case 3 -> Meal.deleteMeal();
                case 4 -> Meal.listMeals();
                case 5 -> back = true;
            }
}
}



    private static void employeeMenu(EmployeeManager employeeManager, CustomerManager customerManager, OrderManager orderManager, BillManager billManager) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Employee Module ---");
            System.out.println("1. Manage Customers");
            System.out.println("------------------------------------");
            System.out.println("2. Make Order");
            System.out.println("------------------------------------");
            System.out.println("3. Cancel Order");
            System.out.println("------------------------------------");
            System.out.println("4. Manage Billing");
            System.out.println("------------------------------------");
            System.out.println("5. Manage Customer Programs");
            System.out.println("------------------------------------");
            System.out.println("6. Back");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 6);
            switch (choice) {
                case 1 -> manageCustomersSubMenu(customerManager);
                case 2 -> orderManager.makeOrder();
                case 3 -> orderManager.cancelOrder();
                case 4 -> manageBillingSubMenu(billManager);
                case 5 -> customerManager.manageCustomerPrograms();
                case 6 -> back = true;
            }
        }
    }


    private static void manageEmployeesSubMenu(EmployeeManager employeeManager) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Employees ---");
            System.out.println("1. Add Employee");
            System.out.println("------------------------------------");
            System.out.println("2. Delete Employee");
            System.out.println("------------------------------------");
            System.out.println("3. Update Employee");
            System.out.println("------------------------------------");
            System.out.println("4. List Employees");
            System.out.println("------------------------------------");
            System.out.println("5. Search Employee");
            System.out.println("------------------------------------");
            System.out.println("6. Back");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 6);
            switch (choice) {
                case 1 -> employeeManager.addEmployee();
                case 2 -> employeeManager.deleteEmployee();
                case 3 -> employeeManager.updateEmployee();
                case 4 -> employeeManager.listEmployees();
                case 5 -> employeeManager.searchEmployee();
                case 6 -> back = true;
            }
        }
    }

    private static void manageCustomersSubMenu(CustomerManager customerManager) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("------------------------------------");
            System.out.println("1. Add Customer");
            System.out.println("------------------------------------");
            System.out.println("2. Delete Customer");
            System.out.println("------------------------------------");
            System.out.println("3. Update Customer");
            System.out.println("------------------------------------");
            System.out.println("4. List Customers");
            System.out.println("------------------------------------");
            System.out.println("5. Search Customer");
            System.out.println("------------------------------------");
            System.out.println("6. View Customer Profile");
            System.out.println("------------------------------------");
            System.out.println("7. Back");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 7);
            switch (choice) {
                case 1 -> customerManager.addCustomer();
                case 2 -> customerManager.deleteCustomer();
                case 3 -> customerManager.updateCustomer();
                case 4 -> customerManager.listCustomers();
                case 5 -> customerManager.searchCustomer();
                case 6 -> customerManager.viewCustomerProfile();
                case 7 -> back = true;
            }
        }
    }

    private static void manageBillingSubMenu(BillManager billManager) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Billing ---");
            System.out.println("------------------------------------");
            System.out.println("1. Add Bill");
            System.out.println("------------------------------------");
            System.out.println("2. List Bills");
            System.out.println("------------------------------------");
            System.out.println("3. Search Bill");
            System.out.println("------------------------------------");
            System.out.println("4. Update Bill");
            System.out.println("------------------------------------");
            System.out.println("5. Delete Bill");
            System.out.println("------------------------------------");
            System.out.println("6. Back");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 6 );
            switch (choice) {
                case 1 -> billManager.AddBill();
                case 2 -> billManager.listBills();
                case 3 -> billManager.SearchBill();
                case 4-> billManager.UpdateBill();
                case 5 -> billManager.DeleteBill();
                case 6 -> back = true;
            }
        }
    }

    private static void viewOrders() {
        System.out.println("\n--- View Orders ---");
        System.out.println("Order viewing functionality not implemented yet.");
        System.out.println("------------------------------------");
        System.out.println("Press Enter to continue...");
        System.out.println("------------------------------------");
        sc.nextLine();
    }

    private static void manageOffers() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Offers ---");
            System.out.println("1. Add Offer");
            System.out.println("------------------------------------");
            System.out.println("2. Delete Offer");
            System.out.println("------------------------------------");
            System.out.println("3. Update Offer");
            System.out.println("------------------------------------");
            System.out.println("4. List Offers");
            System.out.println("------------------------------------");
            System.out.println("5. Search Offer");
            System.out.println("------------------------------------");
            System.out.println("6. Assign Offer to Customer");
            System.out.println("------------------------------------");
            System.out.println("7. Back");

            int choice = getUserChoice(1, 7);
            switch (choice) {
                case 1 -> OfferManager.addOffer();
                case 2 -> OfferManager.deleteOffer();
                case 3 -> OfferManager.updateOffer();
                case 4 -> OfferManager.listOffers();
                case 5 -> OfferManager.searchOffer();
                case 6 -> OfferManager.assignOfferToCustomer();
                case 7 -> back = true;
            }
        }
    }

    private static void customerMenu(CustomerManager customerManager, int customerId) {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== Customer Panel =====");
            System.out.println("1. View My Profile");
            System.out.println("------------------------------------");
            System.out.println("2. Program Registration (Marketing, Loyalty, Reward)");
            System.out.println("------------------------------------");
            System.out.println("3. View Available Offers");
            System.out.println("------------------------------------");
            System.out.println("4. View My Gifts");
            System.out.println("------------------------------------");
            System.out.println("5. Back to Main Menu");
            System.out.println("------------------------------------");

            int choice = getUserChoice(1, 5);
            switch (choice) {
                case 1 -> customerManager.viewSelfProfile(customerId);
                case 2 -> customerManager.selfServiceProgramRegistration(customerId);
                case 3 -> {
                    System.out.println("\n--- Available Offers ---");
                    OfferManager.listOffers();
                }
                case 4 -> {
                    Customer customer = customerManager.getCustomerById(customerId);
                    if (customer != null && !customer.getGifts().isEmpty()) {
                        System.out.println("\n--- Your Gifts ---");
                        for (String gift : customer.getGifts()) {
                            System.out.println("- " + gift);
                        }
                    } else {
                        System.out.println("You don't have any gifts yet.");
                    }
                }
                case 5 -> back = true;
            }
        }
    }

    public static int getUserChoice(int min, int max) {
        int choice = -1;
        while (true) {
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid option. Please choose between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

//package restaurantmanagementsystem;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        // Create and show the GUI
//        SwingUtilities.invokeLater(() -> {
//            new RestaurantManagementGUI().setVisible(true);
//        });
//    }
//}
//
//class RestaurantManagementGUI extends JFrame {
//    private CardLayout cardLayout;
//    private JPanel mainPanel;
//    private LoginPanel loginPanel;
//    private AdminPanel adminPanel;
//    private EmployeePanel employeePanel;
//    private CustomerPanel customerPanel;
//
//    public RestaurantManagementGUI() {
//        setTitle("Restaurant Management System");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(900, 650);
//        setLocationRelativeTo(null);
//
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//
//        // Create panels
//        loginPanel = new LoginPanel(this);
//        adminPanel = new AdminPanel(this);
//        employeePanel = new EmployeePanel(this);
//        customerPanel = new CustomerPanel(this);
//
//        // Add panels to main panel
//        mainPanel.add(loginPanel, "LOGIN");
//        mainPanel.add(adminPanel, "ADMIN");
//        mainPanel.add(employeePanel, "EMPLOYEE");
//        mainPanel.add(customerPanel, "CUSTOMER");
//
//        add(mainPanel);
//        showLoginPanel();
//    }
//
//    public void showLoginPanel() {
//        cardLayout.show(mainPanel, "LOGIN");
//    }
//
//    public void showAdminPanel() {
//        adminPanel.refresh();
//        cardLayout.show(mainPanel, "ADMIN");
//    }
//
//    public void showEmployeePanel() {
//        employeePanel.refresh();
//        cardLayout.show(mainPanel, "EMPLOYEE");
//    }
//
//    public void showCustomerPanel(int customerId) {
//        customerPanel.setCustomerId(customerId);
//        customerPanel.refresh();
//        cardLayout.show(mainPanel, "CUSTOMER");
//    }
//}
//
//class LoginPanel extends JPanel {
//    private RestaurantManagementGUI mainFrame;
//    private JComboBox<String> userTypeCombo;
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JTextField customerIdField;
//
//    public LoginPanel(RestaurantManagementGUI mainFrame) {
//        this.mainFrame = mainFrame;
//        initialize();
//    }
//
//    private void initialize() {
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JLabel titleLabel = new JLabel("Restaurant Management System");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        add(titleLabel, gbc);
//
//        JLabel userTypeLabel = new JLabel("Login as:");
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        add(userTypeLabel, gbc);
//
//        userTypeCombo = new JComboBox<>(new String[]{"Admin", "Employee", "Customer"});
//        gbc.gridx = 1;
//        add(userTypeCombo, gbc);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        add(usernameLabel, gbc);
//
//        usernameField = new JTextField(20);
//        gbc.gridx = 1;
//        add(usernameField, gbc);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        add(passwordLabel, gbc);
//
//        passwordField = new JPasswordField(20);
//        gbc.gridx = 1;
//        add(passwordField, gbc);
//
//        JLabel customerIdLabel = new JLabel("Customer ID:");
//        customerIdLabel.setVisible(false);
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        add(customerIdLabel, gbc);
//
//        customerIdField = new JTextField(20);
//        customerIdField.setVisible(false);
//        gbc.gridx = 1;
//        add(customerIdField, gbc);
//
//        JButton loginButton = new JButton("Login");
//        gbc.gridx = 0;
//        gbc.gridy = 5;
//        gbc.gridwidth = 2;
//        gbc.fill = GridBagConstraints.CENTER;
//        add(loginButton, gbc);
//
//        userTypeCombo.addActionListener(e -> {
//            String selected = (String) userTypeCombo.getSelectedItem();
//            customerIdLabel.setVisible("Customer".equals(selected));
//            customerIdField.setVisible("Customer".equals(selected));
//        });
//
//        loginButton.addActionListener(e -> {
//            String userType = (String) userTypeCombo.getSelectedItem();
//            String username = usernameField.getText();
//            String password = new String(passwordField.getPassword());
//
//            try {
//                if ("Admin".equals(userType)) {
//                    AdminManager adminManager = new AdminManager();
//                    if (adminManager.login()) {
//                        mainFrame.showAdminPanel();
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Invalid admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else if ("Employee".equals(userType)) {
//                    EmployeeManager employeeManager = new EmployeeManager();
//                    if (employeeManager.login()) {
//                        mainFrame.showEmployeePanel();
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Invalid employee credentials", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else if ("Customer".equals(userType)) {
//                    CustomerManager customerManager = new CustomerManager();
//                    if (customerManager.login()) {
//                        int customerId = Integer.parseInt(customerIdField.getText());
//                        mainFrame.showCustomerPanel(customerId);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Invalid customer credentials", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Error during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//    }
//}
//
//class AdminPanel extends JPanel {
//    private RestaurantManagementGUI mainFrame;
//    private JTabbedPane tabbedPane;
//
//    public AdminPanel(RestaurantManagementGUI mainFrame) {
//        this.mainFrame = mainFrame;
//        initialize();
//    }
//
//    private void initialize() {
//        setLayout(new BorderLayout());
//
//        // Create menu bar
//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem logoutItem = new JMenuItem("Logout");
//        logoutItem.addActionListener(e -> mainFrame.showLoginPanel());
//        fileMenu.add(logoutItem);
//        menuBar.add(fileMenu);
//        add(menuBar, BorderLayout.NORTH);
//
//        // Create tabbed pane
//        tabbedPane = new JTabbedPane();
//
//        // Employee Management Tab
//        JPanel employeePanel = new EmployeeManagementPanel();
//        tabbedPane.addTab("Employee Management", employeePanel);
//
//        // Meal Management Tab
//        JPanel mealPanel = new MealManagementPanel();
//        tabbedPane.addTab("Meal Management", mealPanel);
//
//        // Offer Management Tab
//        JPanel offerPanel = new OfferManagementPanel();
//        tabbedPane.addTab("Offer Management", offerPanel);
//
//        // Reports Tab
//        JPanel reportPanel = new ReportPanel();
//        tabbedPane.addTab("Reports", reportPanel);
//
//        add(tabbedPane, BorderLayout.CENTER);
//    }
//
//    public void refresh() {
//        // Refresh data when panel is shown
//    }
//}
//
//class EmployeeManagementPanel extends JPanel {
//    public EmployeeManagementPanel() {
//        setLayout(new BorderLayout());
//
//        JButton addEmployeeBtn = new JButton("Add Employee");
//        JButton listEmployeesBtn = new JButton("List Employees");
//        JButton updateEmployeeBtn = new JButton("Update Employee");
//        JButton deleteEmployeeBtn = new JButton("Delete Employee");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(addEmployeeBtn);
//        buttonPanel.add(listEmployeesBtn);
//        buttonPanel.add(updateEmployeeBtn);
//        buttonPanel.add(deleteEmployeeBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        EmployeeManager employeeManager = new EmployeeManager();
//
//        addEmployeeBtn.addActionListener(e -> {
//            // Implement add employee dialog
//            JOptionPane.showMessageDialog(this, "Add Employee functionality would go here");
//        });
//
//        listEmployeesBtn.addActionListener(e -> {
//            // This would normally show in the GUI, but using your existing console output
//            outputArea.setText("");
//            // For demo, we'll just show a message
//            JOptionPane.showMessageDialog(this, "Employee list would be displayed here");
//        });
//    }
//}
//
//class MealManagementPanel extends JPanel {
//    public MealManagementPanel() {
//        setLayout(new BorderLayout());
//
//        JButton addMealBtn = new JButton("Add Meal");
//        JButton listMealsBtn = new JButton("List Meals");
//        JButton updateMealBtn = new JButton("Update Meal");
//        JButton deleteMealBtn = new JButton("Delete Meal");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(addMealBtn);
//        buttonPanel.add(listMealsBtn);
//        buttonPanel.add(updateMealBtn);
//        buttonPanel.add(deleteMealBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        addMealBtn.addActionListener(e -> {
//            JPanel panel = new JPanel(new GridLayout(0, 1));
//            JTextField nameField = new JTextField();
//            JTextField priceField = new JTextField();
//
//            panel.add(new JLabel("Meal Name:"));
//            panel.add(nameField);
//            panel.add(new JLabel("Price:"));
//            panel.add(priceField);
//
//            int result = JOptionPane.showConfirmDialog(
//                    null, panel, "Add New Meal",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//            if (result == JOptionPane.OK_OPTION) {
//                try {
//                    String name = nameField.getText();
//                    double price = Double.parseDouble(priceField.getText());
//                    Meal.addMeal(); // This would use your existing method
//                    outputArea.append("Added meal: " + name + " - " + price + "\n");
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(this, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//
//        listMealsBtn.addActionListener(e -> {
//            outputArea.setText("");
//            // Normally you would display meals in the GUI
//            Meal.listMeals(); // This shows in console per your existing code
//            outputArea.append("Meal list displayed in console\n");
//        });
//    }
//}
//
//class OfferManagementPanel extends JPanel {
//    public OfferManagementPanel() {
//        setLayout(new BorderLayout());
//
//        JButton addOfferBtn = new JButton("Add Offer");
//        JButton listOffersBtn = new JButton("List Offers");
//        JButton updateOfferBtn = new JButton("Update Offer");
//        JButton deleteOfferBtn = new JButton("Delete Offer");
//        JButton assignOfferBtn = new JButton("Assign Offer");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(addOfferBtn);
//        buttonPanel.add(listOffersBtn);
//        buttonPanel.add(updateOfferBtn);
//        buttonPanel.add(deleteOfferBtn);
//        buttonPanel.add(assignOfferBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        addOfferBtn.addActionListener(e -> {
//            OfferManager.addOffer(); // Uses your existing console-based method
//            outputArea.append("Offer added (check console for details)\n");
//        });
//
//        listOffersBtn.addActionListener(e -> {
//            outputArea.setText("");
//            OfferManager.listOffers(); // Shows in console
//            outputArea.append("Offers listed in console\n");
//        });
//    }
//}
//
//class ReportPanel extends JPanel {
//    public ReportPanel() {
//        setLayout(new BorderLayout());
//
//        JButton generateReportBtn = new JButton("Generate Reports");
//        JTextArea reportArea = new JTextArea();
//        reportArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(reportArea);
//
//        add(generateReportBtn, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        generateReportBtn.addActionListener(e -> {
//            AdminReport adminReport = new AdminReport();
//            adminReport.showAllReports(); // Shows in console
//            reportArea.setText("Reports generated in console\n");
//        });
//    }
//}
//
//class EmployeePanel extends JPanel {
//    private RestaurantManagementGUI mainFrame;
//    private JTabbedPane tabbedPane;
//
//    public EmployeePanel(RestaurantManagementGUI mainFrame) {
//        this.mainFrame = mainFrame;
//        initialize();
//    }
//
//    private void initialize() {
//        setLayout(new BorderLayout());
//
//        // Menu bar
//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem logoutItem = new JMenuItem("Logout");
//        logoutItem.addActionListener(e -> mainFrame.showLoginPanel());
//        fileMenu.add(logoutItem);
//        menuBar.add(fileMenu);
//        add(menuBar, BorderLayout.NORTH);
//
//        // Tabbed pane
//        tabbedPane = new JTabbedPane();
//
//        // Customer Management
//        JPanel customerPanel = new CustomerManagementPanel();
//        tabbedPane.addTab("Customer Management", customerPanel);
//
//        // Order Management
//        JPanel orderPanel = new OrderManagementPanel();
//        tabbedPane.addTab("Order Management", orderPanel);
//
//        // Billing
//        JPanel billingPanel = new BillingPanel();
//        tabbedPane.addTab("Billing", billingPanel);
//
//        // Customer Programs
//        JPanel programsPanel = new CustomerProgramsPanel();
//        tabbedPane.addTab("Customer Programs", programsPanel);
//
//        add(tabbedPane, BorderLayout.CENTER);
//    }
//
//    public void refresh() {
//        // Refresh data when panel is shown
//    }
//}
//
//class CustomerManagementPanel extends JPanel {
//    public CustomerManagementPanel() {
//        setLayout(new BorderLayout());
//
//        JButton addCustomerBtn = new JButton("Add Customer");
//        JButton listCustomersBtn = new JButton("List Customers");
//        JButton updateCustomerBtn = new JButton("Update Customer");
//        JButton deleteCustomerBtn = new JButton("Delete Customer");
//        JButton searchCustomerBtn = new JButton("Search Customer");
//        JButton viewProfileBtn = new JButton("View Profile");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(addCustomerBtn);
//        buttonPanel.add(listCustomersBtn);
//        buttonPanel.add(updateCustomerBtn);
//        buttonPanel.add(deleteCustomerBtn);
//        buttonPanel.add(searchCustomerBtn);
//        buttonPanel.add(viewProfileBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        CustomerManager customerManager = new CustomerManager();
//
//        addCustomerBtn.addActionListener(e -> {
//            customerManager.addCustomer(); // Uses your existing console-based method
//            outputArea.append("Customer added (check console for details)\n");
//        });
//
//        listCustomersBtn.addActionListener(e -> {
//            outputArea.setText("");
//            customerManager.listCustomers(); // Shows in console
//            outputArea.append("Customers listed in console\n");
//        });
//    }
//}
//
//class OrderManagementPanel extends JPanel {
//    public OrderManagementPanel() {
//        setLayout(new BorderLayout());
//
//        JButton makeOrderBtn = new JButton("Make Order");
//        JButton cancelOrderBtn = new JButton("Cancel Order");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(makeOrderBtn);
//        buttonPanel.add(cancelOrderBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        OrderManager orderManager = new OrderManager();
//
//        makeOrderBtn.addActionListener(e -> {
//            orderManager.makeOrder(); // Uses your existing console-based method
//            outputArea.append("Order created (check console for details)\n");
//        });
//
//        cancelOrderBtn.addActionListener(e -> {
//            orderManager.cancelOrder(); // Uses your existing console-based method
//            outputArea.append("Order cancellation processed (check console)\n");
//        });
//    }
//}
//
//class BillingPanel extends JPanel {
//    public BillingPanel() {
//        setLayout(new BorderLayout());
//
//        JButton addBillBtn = new JButton("Add Bill");
//        JButton listBillsBtn = new JButton("List Bills");
//        JButton searchBillBtn = new JButton("Search Bill");
//        JButton updateBillBtn = new JButton("Update Bill");
//        JButton deleteBillBtn = new JButton("Delete Bill");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(addBillBtn);
//        buttonPanel.add(listBillsBtn);
//        buttonPanel.add(searchBillBtn);
//        buttonPanel.add(updateBillBtn);
//        buttonPanel.add(deleteBillBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        BillManager billManager = new BillManager();
//
//        addBillBtn.addActionListener(e -> {
//            billManager.AddBill(); // Uses your existing console-based method
//            outputArea.append("Bill added (check console for details)\n");
//        });
//
//        listBillsBtn.addActionListener(e -> {
//            outputArea.setText("");
//            billManager.listBills(); // Shows in console
//            outputArea.append("Bills listed in console\n");
//        });
//    }
//}
//
//class CustomerProgramsPanel extends JPanel {
//    public CustomerProgramsPanel() {
//        setLayout(new BorderLayout());
//
//        JButton manageProgramsBtn = new JButton("Manage Customer Programs");
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(manageProgramsBtn);
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(buttonPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        CustomerManager customerManager = new CustomerManager();
//
//        manageProgramsBtn.addActionListener(e -> {
//            customerManager.manageCustomerPrograms(); // Uses your existing console-based method
//            outputArea.append("Customer programs managed (check console)\n");
//        });
//    }
//}
//
//class CustomerPanel extends JPanel {
//    private RestaurantManagementGUI mainFrame;
//    private int customerId;
//    private JLabel welcomeLabel;
//    private JTabbedPane tabbedPane;
//
//    public CustomerPanel(RestaurantManagementGUI mainFrame) {
//        this.mainFrame = mainFrame;
//        initialize();
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    private void initialize() {
//        setLayout(new BorderLayout());
//
//        // Menu bar
//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem logoutItem = new JMenuItem("Logout");
//        logoutItem.addActionListener(e -> mainFrame.showLoginPanel());
//        fileMenu.add(logoutItem);
//        menuBar.add(fileMenu);
//        add(menuBar, BorderLayout.NORTH);
//
//        welcomeLabel = new JLabel("Welcome, Customer!");
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        add(welcomeLabel, BorderLayout.NORTH);
//
//        // Tabbed pane
//        tabbedPane = new JTabbedPane();
//
//        // Profile Tab
//        JPanel profilePanel = new CustomerProfilePanel();
//        tabbedPane.addTab("My Profile", profilePanel);
//
//        // Programs Tab
//        JPanel programsPanel = new CustomerProgramsRegistrationPanel();
//        tabbedPane.addTab("Programs", programsPanel);
//
//        // Offers Tab
//        JPanel offersPanel = new CustomerOffersPanel();
//        tabbedPane.addTab("Offers", offersPanel);
//
//        // Gifts Tab
//        JPanel giftsPanel = new CustomerGiftsPanel();
//        tabbedPane.addTab("My Gifts", giftsPanel);
//
//        add(tabbedPane, BorderLayout.CENTER);
//    }
//
//    public void refresh() {
//        welcomeLabel.setText("Welcome, Customer #" + customerId + "!");
//    }
//}
//
//class CustomerProfilePanel extends JPanel {
//    public CustomerProfilePanel() {
//        setLayout(new BorderLayout());
//
//        JButton viewProfileBtn = new JButton("View My Profile");
//        JTextArea profileArea = new JTextArea();
//        profileArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(profileArea);
//
//        add(viewProfileBtn, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        viewProfileBtn.addActionListener(e -> {
//            // Normally you would display the profile in the GUI
//            profileArea.setText("Customer profile would be displayed here\n");
//        });
//    }
//}
//
//class CustomerProgramsRegistrationPanel extends JPanel {
//    public CustomerProgramsRegistrationPanel() {
//        setLayout(new BorderLayout());
//
//        JButton manageProgramsBtn = new JButton("Manage My Programs");
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//
//        add(manageProgramsBtn, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        manageProgramsBtn.addActionListener(e -> {
//            // Normally you would have a GUI for this
//            outputArea.setText("Program management would be handled here\n");
//        });
//    }
//}
//
//class CustomerOffersPanel extends JPanel {
//    public CustomerOffersPanel() {
//        setLayout(new BorderLayout());
//
//        JButton viewOffersBtn = new JButton("View Available Offers");
//        JTextArea offersArea = new JTextArea();
//        offersArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(offersArea);
//
//        add(viewOffersBtn, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        viewOffersBtn.addActionListener(e -> {
//            OfferManager.listOffers(); // Shows in console
//            offersArea.setText("Available offers listed in console\n");
//        });
//    }
//}
//
//class CustomerGiftsPanel extends JPanel {
//    public CustomerGiftsPanel() {
//        setLayout(new BorderLayout());
//
//        JButton viewGiftsBtn = new JButton("View My Gifts");
//        JTextArea giftsArea = new JTextArea();
//        giftsArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(giftsArea);
//
//        add(viewGiftsBtn, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        viewGiftsBtn.addActionListener(e -> {
//            // Normally you would display gifts in the GUI
//            giftsArea.setText("Your gifts would be listed here\n");
//        });
//    }
//}