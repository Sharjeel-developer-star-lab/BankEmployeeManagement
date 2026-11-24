
package model;

public enum ManagerType {

    HEAD_MANAGER("Head Manager"),
    SENIOR_MANAGER("Senior Manager"),
    MANAGER("Manager"),
    ASSISTANT_MANAGER("Assistant Manager"),
    TEAM_LEAD("Team Lead"),
    STAFF_MEMBER("Staff Member");

    private final String label;

    ManagerType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
