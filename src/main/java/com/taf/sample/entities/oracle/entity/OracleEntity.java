package com.taf.sample.entities.oracle.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@Entity
@Table(name = "NAME", catalog = "CATALOG", schema = "SCHEMA")
public class OracleEntity {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "name")
    private String name;
}
