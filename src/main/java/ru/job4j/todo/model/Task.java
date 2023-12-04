package ru.job4j.todo.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;
    private final String title;
    private final String description;
    private final boolean done;
    private final LocalDateTime created;


}
