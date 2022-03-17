package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "email")
public class Email extends BaseEntity{
    private String name;
}
