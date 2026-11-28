package view;

import model.MenuOption;

public class EmployeeView {

    // Display the main menu using enums
    public void displayMenu() {

        System.out.println("\n====================================");
        System.out.println("BANK EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("====================================");

        for (MenuOption option : MenuOption.values()) {

            System.out.println(option.getNumber()
                    + ". " + option.getLabel());
        }

        System.out.print("\nEnter your choice: ");
    }

    // Display messages to the user
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Display input prompts
    public void displayPrompt(String prompt) {
        System.out.print(prompt);
    }
}