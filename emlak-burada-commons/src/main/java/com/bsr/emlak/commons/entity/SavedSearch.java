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
}
