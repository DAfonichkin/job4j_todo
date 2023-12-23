package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "todo_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private String password;
}