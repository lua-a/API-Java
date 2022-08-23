package com.api.servicecontrol.dtos;

import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.time.LocalDate;

public class OrderDto {

    @NotNull
    private Integer number;

    @NotNull
    private Double percentualDiscount;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPercentualDiscount() {
        return percentualDiscount;
    }

    public void setPercentualDiscount(Double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }
}