package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Collection;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, HttpSession session, @SessionAttribute User user) {
        model.addAttribute("tasks", convertTime(taskService.findAll(), user));
        return "index";
    }

    @GetMapping({"/done"})
    public String getDone(Model model, HttpSession session, @SessionAttribute User user) {
        model.addAttribute("tasks", convertTime(taskService.findByDone(true), user));
        return "index";
    }

    @GetMapping({"/new"})
    public String getNew(Model model, HttpSession session, @SessionAttribute User user) {
        model.addAttribute("tasks", convertTime(taskService.findByDone(false), user));
        return "index";
    }

    private Collection<Task> convertTime(Collection<Task> tasks, User user) {
        for (Task task : tasks) {
            task.setCreated(task.getCreated()
                    .atZone(ZoneId.of("UTC"))
                    .withZoneSameInstant(ZoneId.of(user.getTimezone().getID())).toLocalDateTime());
        }
        return tasks;
    }

}