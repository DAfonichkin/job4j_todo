package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;
import java.util.TimeZone;

@Entity
@Table(name = "todo_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    @Column
    private TimeZone timezone;
}