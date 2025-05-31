package restaurantmanagementsystem;

import java.io.*;
import java.util.*;

public class Meal {
    private String name;
    private double price;

    public Meal(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toFileString() {
        return name + "," + price;
    }

    public void printMeal() {
        System.out.println("Meal Name: " + name);
        System.out.println("Price: " + price);
    }

    // ==========================
    // Static Utility
    // ==========================

    private static String readValidName(Scanner sc) {
        while (true) {
            System.out.print("Enter meal name: ");
            String name = sc.nextLine();
            if (name.matches(".*\\d.*")) {
                System.out.println("input is invalid please try again.");
            } else {
                return name;
            }
        }
    }

    private static double readValidPrice(Scanner sc) {
        while (true) {
            System.out.print("Enter meal price: ");
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("invalid input please try again.");
            }
        }
    }

    // ==========================
    // Static Admin Functions
    // ==========================

    public static void addMeal() {
        Scanner sc = new Scanner(System.in);
        String name = readValidName(sc);
        double price = readValidPrice(sc);

        Meal newMeal = new Meal(name, price);

        try (FileWriter fw = new FileWriter("meals.txt", true)) {
            fw.write(newMeal.toFileString() + "\n");
            System.out.println("Meal added and saved.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static void listMeals() {
        List<Meal> meals = readMealsFromFile();

        if (meals.isEmpty()) {
            System.out.println("the file is empty please add meals to list them.");
            return;
        }

        for (Meal m : meals) {
            m.printMeal();
            System.out.println("---------------");
        }
    }

    public static void searchMeal() {
        List<Meal> meals = readMealsFromFile();

        if (meals.isEmpty()) {
            System.out.println("the file is empty we can not find anythingً.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String name = readValidName(sc);

        boolean found = false;
        for (Meal m : meals) {
            if (m.getName().equalsIgnoreCase(name)) {
                m.printMeal();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Meal not found.");
        }
    }

    public static void deleteMeal() {
        List<Meal> meals = readMealsFromFile();

        if (meals.isEmpty()) {
            System.out.println("we can't delete meal,please add meals before thatً.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String name = readValidName(sc);

        boolean found = false;
        Iterator<Meal> it = meals.iterator();
        while (it.hasNext()) {
            Meal m = it.next();
            if (m.getName().equalsIgnoreCase(name)) {
                it.remove();
                found = true;
                break;
            }
        }

        if (found) {
            writeMealsToFile(meals);
            System.out.println("Meal deleted.");
        } else {
            System.out.println("Meal not found.");
        }
    }

    public static void updateMeal() {
        List<Meal> meals = readMealsFromFile();

        if (meals.isEmpty()) {
            System.out.println("we can't update meal , the file is empty please add meals before thatً.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String name = readValidName(sc);

        boolean found = false;
        for (Meal m : meals) {
            if (m.getName().equalsIgnoreCase(name)) {
                System.out.println("enter new name and price:");
                String newName = readValidName(sc);
                double newPrice = readValidPrice(sc);

                m.name = newName;
                m.price = newPrice;
                found = true;
                break;
            }
        }

        if (found) {
            writeMealsToFile(meals);
            System.out.println("Meal updated.");
        } else {
            System.out.println("Meal not found.");
        }
    }

    // =======================
    // File Handling Methods
    // =======================

    private static List<Meal> readMealsFromFile() {
        List<Meal> meals = new ArrayList<>();
        File file = new File("meals.txt");
        if (!file.exists()) return meals;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    meals.add(new Meal(name, price));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading from file.");
        }

        return meals;
    }

    private static void writeMealsToFile(List<Meal> meals) {
        try (FileWriter fw = new FileWriter("meals.txt", false)) {
            for (Meal m : meals) {
                fw.write(m.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}