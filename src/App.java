
import model.Employee;
import model.EmployeeModel;
import algorithms.MergeSort;
import java.io.IOException;

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

        } catch (IOException e) {

            System.out.println("Error reading file: "
                    + e.getMessage());
        }
    }
}
