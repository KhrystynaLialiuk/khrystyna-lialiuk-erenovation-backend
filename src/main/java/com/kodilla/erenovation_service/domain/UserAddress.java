package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAddress {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "STREET")
    private String street;

    @NotNull
    @Column(name = "BUILDING")
    private int building;

    @NotNull
    @Column(name = "APARTMENT")
    private int apartment;

    @NotNull
    @Column(name = "POSTAL_CODE")
    private String postalCode;
}
