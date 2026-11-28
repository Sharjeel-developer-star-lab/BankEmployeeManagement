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
                    view.displayMessage("Please enter a valid number.");
                    continue;
                }

                MenuOption selected = MenuOption.fromNumber(choice);
                if (selected == null) {
                    view.displayMessage("Invalid menu option.");
                    continue;

                }

                switch (selected) {

                    case SORT:
                        sortEmployees(model, view);
                        break;

                    case SEARCH:
                        searchEmployees(model, scanner, view);
                        break;

                    case ADD_RECORDS:
                        addEmployees(model, scanner, view);
                        break;

                    case CREATE_BINARY_TREE:
                        createBinaryTree(model, view);
                        break;
                    case EXIT:
                        running = false;
                        view.displayMessage("Program closed successfully.");
                        ;
                        break;
                }
            }

        } catch (IOException e) {

            view.displayMessage("Error reading file: "
                    + e.getMessage());
        }
    }

    // Sort and display the first 20 employees
    private static void sortEmployees(EmployeeModel model, EmployeeView view) {

        MergeSort.sort(model.getEmployees());

        view.displayMessage("\nSORT selected");
        view.displayMessage("First 20 employees:");

        int limit = Math.min(20, model.getEmployees().size());

        for (int i = 0; i < limit; i++) {

            Employee employee = model.getEmployees().get(i);

            view.displayMessage((i + 1) + ". "
                    + employee.getName());
        }
    }

    // Search for an employee by name
    private static void searchEmployees(
            EmployeeModel model, Scanner scanner, EmployeeView view) {

        // Binary Search requires sorted data
        MergeSort.sort(model.getEmployees());

        view.displayMessage("\nSEARCH selected");
        view.displayPrompt("Enter employee name to search: ");
        String name = scanner.nextLine().trim();

        Employee result = BinarySearch.search(
                model.getEmployees(),
                name,
                0,
                model.getEmployees().size() - 1);

        if (result != null) {
            view.displayMessage("\nEmployee found:");
            view.displayMessage(result.toString());
        } else {
            view.displayMessage("Employee not found.");
        }
    }

    // Add and validate new employee records
    private static void addEmployees(
            EmployeeModel model, Scanner scanner, EmployeeView view) {
        view.displayMessage("\nADD RECORDS selected");

        view.displayPrompt("Enter employee name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            view.displayMessage("Name cannot be empty.");
            return;
        }

        // Display available manager types
        ManagerType[] types = ManagerType.values();

        view.displayMessage("\nSelect Manager Type:");

        for (int i = 0; i < types.length; i++) {
            view.displayMessage((i + 1) + ". " + types[i]);
        }

        int managerChoice;

        try {
            managerChoice = Integer.parseInt(
                    scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            view.displayMessage("Invalid manager choice.");
            return;
        }

        if (managerChoice < 1 || managerChoice > types.length) {
            view.displayMessage("Invalid manager choice.");
            return;
        }

        // Display available departments
        Department[] departments = Department.values();

        view.displayMessage("\nSelect Department:");
        for (int i = 0; i < departments.length; i++) {
            view.displayMessage((i + 1) + ". " + departments[i]);
        }

        int departmentChoice;

        try {
            departmentChoice = Integer.parseInt(
                    scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            view.displayMessage("Invalid department choice.");
            return;
        }

        if (departmentChoice < 1
                || departmentChoice > departments.length) {
            view.displayMessage("Invalid department choice.");
            return;
        }

        // Create and store the new employee
        Employee employee = new Employee(
                name,
                types[managerChoice - 1],
                departments[departmentChoice - 1]);

        model.addEmployee(employee);

        view.displayMessage("\nEmployee added successfully:");

        view.displayMessage("\nAll newly added employees:");

        for (Employee newEmployee : model.getNewlyAddedEmployees()) {
            view.displayMessage(newEmployee.toString());
        }
    }

    // Create and display the employee hierarchy
    private static void createBinaryTree(EmployeeModel model, EmployeeView view) {

        view.displayMessage("\nCREATE BINARY TREE selected");
        if (model.getEmployees().size() < 20) {
            view.displayMessage("At least 20 employees are required.");
            return;
        }

        EmployeeBinaryTree tree = new EmployeeBinaryTree();

        // Insert employee records into the binary tree
        for (Employee employee : model.getEmployees()) {
            tree.insert(employee);
        }

        view.displayMessage("\nEmployee Hierarchy:");

        tree.displayLevelOrder();

        view.displayMessage("\nTree height (levels): "
                + tree.getHeight());

        view.displayMessage("Total node count: "
                + tree.getNodeCount());
    }

}
