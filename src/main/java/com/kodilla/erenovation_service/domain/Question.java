package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "QUESTION")
    private String question;

    @NotNull
    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "ANSWER")
    private String answer;
}
