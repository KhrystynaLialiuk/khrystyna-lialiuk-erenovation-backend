package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "PHONE")
    private String phone;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    private UserAddress userAddress;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PASSWORD_ID")
    private UserPassword userPassword;

    @OneToMany(
            targetEntity = Pricing.class,
            mappedBy = "user"
    )
    private List<Pricing> pricingList = new ArrayList<>();

    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "user"
    )
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(
            targetEntity = Question.class,
            mappedBy = "user"
    )
    private List<Question> questions = new ArrayList<>();
}
