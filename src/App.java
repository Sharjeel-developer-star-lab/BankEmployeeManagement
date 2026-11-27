
import model.Employee;
import model.EmployeeModel;
import algorithms.MergeSort;
import algorithms.BinarySearch;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        EmployeeModel model = new EmployeeModel();

        try {

            model.loadEmployees("Applicants_Form.txt");

            System.out.println("File read successfully");

            System.out.println("Total employees: "
                    + model.getEmployees().size());

            // Sort employees alphabetically
            MergeSort.sort(model.getEmployees());

            System.out.println("\nSORT selected");
            System.out.println("First 20 employees:");

            // Display only the first 20 names
            int limit = Math.min(20, model.getEmployees().size());

            for (int i = 0; i < limit; i++) {

                Employee employee = model.getEmployees().get(i);

                System.out.println((i + 1) + ". "
                        + employee.getName());
            }

            // Test recursive Binary Search
            Scanner searchScanner = new Scanner(System.in);

            System.out.print("\nEnter employee name to search: ");

            String searchName = searchScanner.nextLine().trim();

            Employee result = BinarySearch.search(
                    model.getEmployees(),
                    searchName,
                    0,
                    model.getEmployees().size() - 1
            );

            if (result != null) {
                System.out.println("\nEmployee found:");
                System.out.println(result);
            } else {
                System.out.println("Employee not found.");
            }
            searchScanner.close();

        } catch (IOException e) {

            System.out.println("Error reading file: "
                    + e.getMessage());
        }
    }
}
