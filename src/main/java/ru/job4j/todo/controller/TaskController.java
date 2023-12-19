package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;


    @GetMapping("/create")
    public String getCreationPage(Model model) {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        try {
            taskService.save(task);
            return "redirect:/";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("tasks/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("tasks/change/{id}")
    public String changeById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/change";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            var isUpdated = taskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Задача с указанным идентификатором не найдена");
                return "errors/404";
            }
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = taskService.delete(taskService.getById(id).get());
        if (!isDeleted) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id) {
        var optionalTask = taskService.getById(id);
        if (!optionalTask.isPresent()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        var task = optionalTask.get();
        task.setDone(true);
        var isDone = taskService.update(task);
        if (!isDone) {
            model.addAttribute("message", "Ошибка при обновлениии задания");
            return "errors/404";
        }
        return "redirect:/";
    }
}