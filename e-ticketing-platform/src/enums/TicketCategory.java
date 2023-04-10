package enums;

public enum TicketCategory {
    VIP(0), GOLDEN_CIRCLE(1), GENERAL_ENTRANCE(2), PASS(5);
    private final int priority;

    public int getPriority() {
        return priority;
    }

    TicketCategory(int priority) {
        this.priority = priority;
    }
}
