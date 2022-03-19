package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "document")
public class Document extends BaseEntity {

    private String url;
    private DocumentType documentType;

    public enum DocumentType {
        PDF,
        IMAGE
    }
}
