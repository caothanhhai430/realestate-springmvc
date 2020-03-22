package com.javaweb.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class RentAreaDTO extends AbstractDTO{
    private Long id;
    private Integer value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
