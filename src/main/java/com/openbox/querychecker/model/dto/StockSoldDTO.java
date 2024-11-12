package com.openbox.querychecker.model.dto;

public class StockSoldDTO {

    private String description;

    public StockSoldDTO() {}

    public StockSoldDTO(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
