package ru.job4j.todo.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private LocalDateTime created;

}
