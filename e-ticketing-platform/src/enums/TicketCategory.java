package enums;

public enum TicketCategory {
    VIP("VIP", 0), GOLDEN_CIRCLE("Golden Circle", 1),
    GENERAL_ENTRANCE("General Entrance", 2),DAY_PASS("Day Pass", 4), PASS("Pass", 5);
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
