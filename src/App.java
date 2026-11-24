
import model.Employee;
import model.ManagerType;
import model.Department;

public class App {

    public static void main(String[] args) {

        Employee employee = new Employee(
            "John Smith",
            ManagerType.SENIOR_MANAGER,
            Department.FINANCE
        );

        System.out.println(employee);
    }
}
