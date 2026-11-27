package tree;

import model.Employee;

public class EmployeeNode {

    Employee employee;
    EmployeeNode left;
    EmployeeNode right;

    // Constructor to create an employee node
    public EmployeeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}