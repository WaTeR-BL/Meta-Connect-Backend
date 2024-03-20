package com.ned.metadata_tool.helper;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.BaseEntity;

import javax.persistence.PrePersist;


public class HibernateEntityListener {
    @PrePersist
    public void persist(BaseEntity o) {
        o.setFlag(Flag.ACTIVE);
    }

}