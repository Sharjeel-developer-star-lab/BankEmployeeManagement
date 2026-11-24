
package model;

public class Employee {

    private final String name;
    private final ManagerType managerType;
    private final Department department;

    // Constructor
    public Employee(String name, ManagerType managerType, Department department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public Department getDepartment() {
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
