package sh.locus.accessmanagement.model;

public enum ActionType {

    WRITE(0, "write"),
    READ(1, "read"),
    DELETE(2, "delete");

    private final int id;
    private final String name;

    ActionType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}