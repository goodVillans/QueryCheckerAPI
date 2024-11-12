package com.openbox.querychecker.model.dto;
import java.time.LocalDateTime;

public class CustomerStatusDTO {
    private String surname;
    private String firstName;
    private Long customerStatusId;
    private LocalDateTime createDateTime;

    public CustomerStatusDTO() {}

    public CustomerStatusDTO(String surname, String firstName, Long customerStatusId, LocalDateTime createDateTime) {
        this.surname = surname;
        this.firstName = firstName;
        this.customerStatusId = customerStatusId;
        this.createDateTime = createDateTime;
    }

    // Getters and Setters
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public Long getCustomerStatusId() { return customerStatusId; }
    public void setCustomerStatusId(Long customerStatusId) { this.customerStatusId = customerStatusId; }
    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public void setCreateDateTime(LocalDateTime createDateTime) { this.createDateTime = createDateTime; }
}
