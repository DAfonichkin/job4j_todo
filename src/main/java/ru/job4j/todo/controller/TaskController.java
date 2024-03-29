package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    private PriorityService priorityService;
    private CategoryService categoryService;

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task,  @SessionAttribute("user") User user,
                         @RequestParam(required = false) Set<Integer> idCategories) {
        if (!idCategories.isEmpty()) {
            task.setCategories(categoryService.findCategoriesById(idCategories));
        }
        task.setUser(user);
        if (taskService.save(task).isPresent()) {
            return "redirect:/";
        }
        return "errors/404";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        model.addAttribute("categories", categoryService.findAll());
        return "tasks/one";
    }

    @GetMapping("/change/{id}")
    public String changeById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/change";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model, @SessionAttribute("user") User user,
                            @RequestParam(required = false) Set<Integer> idCategories) {
        task.setUser(user);
        if (!idCategories.isEmpty()) {
            task.setCategories(categoryService.findCategoriesById(idCategories));
        }
        if (!taskService.update(task)) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        if (!taskService.delete(taskService.getById(id).get())) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id) {
        if (!taskService.setDone(id)) {
            model.addAttribute("message", "Ошибка при обновлениии задания");
            return "errors/404";
        }
        return "redirect:/";
    }
}