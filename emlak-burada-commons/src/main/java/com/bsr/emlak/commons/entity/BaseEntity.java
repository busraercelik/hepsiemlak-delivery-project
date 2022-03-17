package com.bsr.emlak.commons.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity extends Identifiable{

    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    protected String createdBy;
    protected String modifiedBy;
}
