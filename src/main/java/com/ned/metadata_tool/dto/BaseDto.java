package com.ned.metadata_tool.dto;

import com.ned.metadata_tool.enums.Flag;

import java.io.Serializable;

public class BaseDto implements Serializable {
    private Long id;
    private Flag flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
