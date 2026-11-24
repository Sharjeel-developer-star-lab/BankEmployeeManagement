
package model;

public enum Department {

    ACCOUNTING("Accounting"),
    CUSTOMER_SERVICE("Customer Service"),
    FINANCE("Finance"),
    HR("HR"),
    IT_DEVELOPMENT("IT Development"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    SALES("Sales"),
    TECHNICAL_SUPPORT("Technical Support");

    private final String label;

    Department(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
