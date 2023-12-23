package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private boolean done;
    @Column
    private LocalDateTime created = LocalDateTime.now();

}
