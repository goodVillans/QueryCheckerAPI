package com.openbox.querychecker.model.dto;

import java.math.BigDecimal;

public class TopCustomerDTO {
    private String firstName;
    private String surname;
    private Integer numberOfSales;
    private BigDecimal totalSaleValue;

    public TopCustomerDTO() {}

    public TopCustomerDTO(String firstName, String surname, Integer numberOfSales, BigDecimal totalSaleValue) {
        this.firstName = firstName;
        this.surname = surname;
        this.numberOfSales = numberOfSales;
        this.totalSaleValue = totalSaleValue;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public Integer getNumberOfSales() { return numberOfSales; }
    public void setNumberOfSales(Integer numberOfSales) { this.numberOfSales = numberOfSales; }
    public BigDecimal getTotalSaleValue() { return totalSaleValue; }
    public void setTotalSaleValue(BigDecimal totalSaleValue) { this.totalSaleValue = totalSaleValue; }
}
