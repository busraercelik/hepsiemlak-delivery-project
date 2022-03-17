package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public abstract class Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
}
