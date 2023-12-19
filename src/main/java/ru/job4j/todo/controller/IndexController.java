package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @GetMapping({"/done"})
    public String getDone(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findDone());
        return "index";
    }

    @GetMapping({"/new"})
    public String getNew(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findNew());
        return "index";
    }

}