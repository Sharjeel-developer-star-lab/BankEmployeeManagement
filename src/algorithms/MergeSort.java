
package algorithms;

import model.Employee;
import java.util.ArrayList;

public class MergeSort {

    // Recursive sorting method
    public static void sort(ArrayList<Employee> employees) {

        // Base case
        if (employees.size() <= 1) {
            return;
        }

        int middle = employees.size() / 2;

        ArrayList<Employee> left = new ArrayList<>(
                employees.subList(0, middle));

        ArrayList<Employee> right = new ArrayList<>(
                employees.subList(middle, employees.size()));

        // Recursive calls
        sort(left);
        sort(right);

        // Merge both sorted lists
        merge(employees, left, right);
    }

    private static void merge(
            ArrayList<Employee> employees,
            ArrayList<Employee> left,
            ArrayList<Employee> right) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {

            if (left.get(i).getName().compareToIgnoreCase(
                    right.get(j).getName()) <= 0) {

                employees.set(k++, left.get(i++));

            } else {

                employees.set(k++, right.get(j++));
            }
        }

        // Copy remaining employees from left
        while (i < left.size()) {
            employees.set(k++, left.get(i++));
        }

        // Copy remaining employees from right
        while (j < right.size()) {
            employees.set(k++, right.get(j++));
        }
    }
}
