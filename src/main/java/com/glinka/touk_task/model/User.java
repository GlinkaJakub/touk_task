package com.glinka.touk_task.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Pattern(regexp = "[A-Z][a-z]*")
    private String name;

    @Pattern(regexp = "[A-Z][a-z]*(-[A-Z][a-z])?")
    private String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
