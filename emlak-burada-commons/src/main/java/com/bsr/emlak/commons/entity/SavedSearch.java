package com.bsr.emlak.commons.entity;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "saved_search")
public class SavedSearch extends BaseEntity{

    private String searchText;

    @PrePersist
    @PreUpdate
    public void updateInternalFields() {
        this.createdAt = ObjectUtils.isEmpty(this.createdAt) ? LocalDateTime.now() : this.createdAt;
        this.modifiedAt = LocalDateTime.now();
    }
}
