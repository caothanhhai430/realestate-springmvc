package com.javaweb.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class RentAreaDTO extends AbstractDTO{
    private Integer id;
    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
