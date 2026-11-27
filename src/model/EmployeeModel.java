
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeModel {

    private final ArrayList<Employee> employees = new ArrayList<>();

    private final ArrayList<Employee> newlyAddedEmployees = new ArrayList<>();

    // Read employee records from the text file
    public void loadEmployees(String filename) throws IOException {

        ArrayList<Employee> loaded = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            // Skip the header row
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 9) {
                    continue;
                }

                String name = data[0].trim() + " "
                        + data[1].trim();

                Department department = findDepartment(data[5].trim());

                ManagerType managerType = findManagerType(data[7].trim());

                if (department != null) {

                    Employee employee = new Employee(
                            name, managerType, department);

                    loaded.add(employee);
                }
            }
        }

        employees.clear();
        employees.addAll(loaded);
    }

    // Match department with enum
    private Department findDepartment(String name) {

        for (Department department : Department.values()) {

            if (department.toString().equalsIgnoreCase(name)) {
                return department;
            }
        }

        return null;
    }

    // Match management role with enum
    private ManagerType findManagerType(String title) {

        for (ManagerType type : ManagerType.values()) {

            if (type.toString().equalsIgnoreCase(title)) {
                return type;
            }
        }

        return ManagerType.STAFF_MEMBER;
    }

    // Return employee records
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Add a new employee to both lists
    public void addEmployee(Employee employee) {
        employees.add(employee);
        newlyAddedEmployees.add(employee);
    }

    // Return all newly added employee records
    public ArrayList<Employee> getNewlyAddedEmployees() {
        return newlyAddedEmployees;
    }
}
