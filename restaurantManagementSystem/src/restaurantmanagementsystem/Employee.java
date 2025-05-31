package restaurantmanagementsystem;

public class Employee {
    private int id;
    private String name;
    private String phone;


    public Employee(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;

    }

    public String toFileString() {
        return id + "," + name + "," + phone  ;
    }


    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Phone: " + phone  ;
    }

    public int getId() {
        return id;
    }

}


