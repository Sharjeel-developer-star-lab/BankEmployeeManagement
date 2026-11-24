
import model.Employee;

public class App {
    public static void main(String[] args) {

        Employee employee = new Employee(
            "John Smith",
            "Senior Manager",
            "Finance"
        );

        System.out.println(employee);
    }
}