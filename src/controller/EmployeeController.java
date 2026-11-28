package controller;

import model.Employee;
import model.EmployeeModel;
import model.MenuOption;
import algorithms.MergeSort;
import algorithms.BinarySearch;
import model.ManagerType;
import model.Department;
import tree.EmployeeBinaryTree;
import java.io.IOException;
import java.util.Scanner;
import view.EmployeeView;

public class EmployeeController {
    public static void main(String[] args) {

        EmployeeModel model = new EmployeeModel();
        EmployeeView view = new EmployeeView();
        try (Scanner scanner = new Scanner(System.in)) {

            view.displayPrompt("Please enter the filename to read: ");
            String filename = scanner.nextLine().trim();

            model.loadEmployees(filename);

            view.displayMessage("File read successfully");

            view.displayMessage("Total employees: "
                    + model.getEmployees().size());

            boolean running = true;

            while (running) {
                view.displayMenu();

                int choice;

                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                MenuOption selected = MenuOption.fromNumber(choice);

                if (selected == null) {
                    System.out.println("Invalid menu option.");
                    continue;
                }

                switch (selected) {

                    case SORT:
                        sortEmployees(model);
                        break;

                    case SEARCH:
                        searchEmployees(model, scanner);
                        break;

                    case ADD_RECORDS:
                        addEmployees(model, scanner);
                        break;

                    case CREATE_BINARY_TREE:
                        createBinaryTree(model);
                        break;
                    case EXIT:
                        running = false;
                        System.out.println("Program closed successfully.");
                        break;
                }
            }

        } catch (IOException e) {

            System.out.println("Error reading file: "
                    + e.getMessage());
        }
    }

    // Sort and display the first 20 employees
    private static void sortEmployees(EmployeeModel model) {

        MergeSort.sort(model.getEmployees());

        System.out.println("\nSORT selected");
        System.out.println("First 20 employees:");

        int limit = Math.min(20, model.getEmployees().size());

        for (int i = 0; i < limit; i++) {

            Employee employee = model.getEmployees().get(i);

            System.out.println((i + 1) + ". "
                    + employee.getName());
        }
    }

    // Search for an employee by name
    private static void searchEmployees(
            EmployeeModel model, Scanner scanner) {

        // Binary Search requires sorted data
        MergeSort.sort(model.getEmployees());

        System.out.println("\nSEARCH selected");
        System.out.print("Enter employee name to search: ");

        String name = scanner.nextLine().trim();

        Employee result = BinarySearch.search(
                model.getEmployees(),
                name,
                0,
                model.getEmployees().size() - 1);

        if (result != null) {
            System.out.println("\nEmployee found:");
            System.out.println(result);
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Add and validate new employee records
    private static void addEmployees(
            EmployeeModel model, Scanner scanner) {

        System.out.println("\nADD RECORDS selected");

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        // Display available manager types
        ManagerType[] types = ManagerType.values();

        System.out.println("\nSelect Manager Type:");

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int managerChoice;

        try {
            managerChoice = Integer.parseInt(
                    scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid manager choice.");
            return;
        }

        if (managerChoice < 1 || managerChoice > types.length) {
            System.out.println("Invalid manager choice.");
            return;
        }

        // Display available departments
        Department[] departments = Department.values();

        System.out.println("\nSelect Department:");

        for (int i = 0; i < departments.length; i++) {
            System.out.println((i + 1) + ". " + departments[i]);
        }

        int departmentChoice;

        try {
            departmentChoice = Integer.parseInt(
                    scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid department choice.");
            return;
        }

        if (departmentChoice < 1
                || departmentChoice > departments.length) {
            System.out.println("Invalid department choice.");
            return;
        }

        // Create and store the new employee
        Employee employee = new Employee(
                name,
                types[managerChoice - 1],
                departments[departmentChoice - 1]);

        model.addEmployee(employee);

        System.out.println("\nEmployee added successfully:");

        System.out.println("\nAll newly added employees:");

        for (Employee newEmployee : model.getNewlyAddedEmployees()) {
            System.out.println(newEmployee);
        }
    }

    // Create and display the employee hierarchy
    private static void createBinaryTree(EmployeeModel model) {

        System.out.println("\nCREATE BINARY TREE selected");

        if (model.getEmployees().size() < 20) {
            System.out.println("At least 20 employees are required.");
            return;
        }

        EmployeeBinaryTree tree = new EmployeeBinaryTree();

        // Insert employee records into the binary tree
        for (Employee employee : model.getEmployees()) {
            tree.insert(employee);
        }

        System.out.println("\nEmployee Hierarchy:");

        tree.displayLevelOrder();

        System.out.println("\nTree height (levels): "
                + tree.getHeight());

        System.out.println("Total node count: "
                + tree.getNodeCount());
    }

}
