package com.api.servicecontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemDto {

    @NotBlank
    private String description;
    @NotNull
    private Double value;
    @NotNull
    private Character type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }
}

