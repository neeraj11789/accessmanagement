package sh.locus.accessmanagement.model;

public enum ActionType {

    WRITE(0, "create"),
    READ(1, "sell"),
    DELETE(2, "Disabled");

    private final int id;
    private final String name;

    ActionType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}