package enums;

public enum TicketCategory {
    VIP("VIP", 0), GOLDEN_CIRCLE("GoldenCircle", 1),
    GENERAL_ENTRANCE("GeneralEntrance", 2),DAY_PASS("DayPass", 4), PASS("Pass", 5);
    private final String name;
    private final int priority;

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    TicketCategory(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
