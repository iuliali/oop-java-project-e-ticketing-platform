package enums;

public enum EventType {
    FESTIVAL ("Festival"), FESTIVAL_DAY ("Festival Day"), CONCERT("Concert"), STAND_UP("Stand-up");

    private final String name;

    public String getName() {
        return name;
    }

    EventType(String name) {
        this.name = name;
    }
}
