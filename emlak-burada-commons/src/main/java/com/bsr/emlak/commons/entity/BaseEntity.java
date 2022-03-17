package com.bsr.emlak.commons.entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

public abstract class BaseEntity extends Identifiable{

    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime modifiedAt;

    protected String createdBy;
    protected String modifiedBy;
}
