package model;

public enum MenuOption {

    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD_RECORDS(3, "ADD RECORDS"),
    CREATE_BINARY_TREE(4, "Create a binary tree"),
    EXIT(5, "EXIT");

    private final int number;
    private final String label;

    MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }

    public static MenuOption fromNumber(int number) {

        for (MenuOption option : values()) {

            if (option.getNumber() == number) {
                return option;
            }
        }

        return null;
    }
}