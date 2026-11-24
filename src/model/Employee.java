
package model;

public class Employee {

    private final String name;
    private final String managerType;
    private final String department;

    // Constructor
    public Employee(String name, String managerType, String department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getManagerType() {
        return managerType;
    }

    public String getDepartment() {
        return department;
    }

    // Display employee information
    @Override
    public String toString() {
        return "Name: " + name
                + " | Manager Type: " + managerType
                + " | Department: " + department;
    }
}
