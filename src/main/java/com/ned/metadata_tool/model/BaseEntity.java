package com.ned.metadata_tool.model;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.helper.HibernateEntityListener;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Audited
@EntityListeners(HibernateEntityListener.class)
public class BaseEntity {
    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(value = EnumType.STRING)
    protected Flag flag;

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
