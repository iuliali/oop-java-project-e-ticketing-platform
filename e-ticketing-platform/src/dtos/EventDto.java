package dtos;

import java.time.LocalDateTime;

public class EventDto {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer locationId;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public EventDto(String name, LocalDateTime startDate, LocalDateTime endDate, Integer locationId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
