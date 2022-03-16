package com.bsr.emlak.email.model;

import javax.persistence.*;

@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
