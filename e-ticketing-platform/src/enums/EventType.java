package enums;

public enum EventType {
    CONCERT("Concert", 0), STAND_UP("Stand-up", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    EventType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
