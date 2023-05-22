package models;

import enums.TicketCategory;

public class MapEventTicketsConfiguration {
    private static Integer idGenerator = 0;
    private Integer id;
    private TicketCategory category;
    private Integer quantity;
    private Integer eventId;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public static Integer getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(Integer idGenerator) {
        MapEventTicketsConfiguration.idGenerator = idGenerator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MapEventTicketsConfiguration(TicketCategory category, Integer quantity) {
        this.id = ++idGenerator;
        this.category = category;
        this.quantity = quantity;
    }

    public MapEventTicketsConfiguration(
            Integer id,
            TicketCategory category,
            Integer quantity,
            Integer eventId) {
        this.id = id;
        this.category = category;
        this.quantity = quantity;
        this.eventId = eventId;
    }


    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
