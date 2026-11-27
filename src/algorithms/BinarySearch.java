package algorithms;

import java.util.ArrayList;
import model.Employee;

public class BinarySearch {

    public static Employee search(
            ArrayList<Employee> employees,
            String name,
            int low,
            int high) {

        // Base case: employee not found
        if (low > high) {
            return null;
        }

        // Calculate the middle index
        int mid = low + (high - low) / 2;

        Employee employee = employees.get(mid);

        // Compare employee names
        int comparison = name.compareToIgnoreCase(
                employee.getName());

        // Employee found
        if (comparison == 0) {
            return employee;
        }

        // Search the left half recursively
        if (comparison < 0) {
            return search(employees, name,
                    low, mid - 1);
        }

        // Search the right half recursively
        return search(employees, name,
                mid + 1, high);
    }
}