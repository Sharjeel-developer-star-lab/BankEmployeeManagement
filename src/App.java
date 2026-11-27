
import model.Employee;
import model.EmployeeModel;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        EmployeeModel model = new EmployeeModel();

        try {

            model.loadEmployees("Applicants_Form.txt");

            System.out.println("File read successfully");

            System.out.println("Total employees: "
                    + model.getEmployees().size());

            for (Employee employee : model.getEmployees()) {
                System.out.println(employee);
            }

        } catch (IOException e) {

            System.out.println("Error reading file: "
                    + e.getMessage());
        }
    }
}
