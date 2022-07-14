package kz.aigulsharip.controller;

import kz.aigulsharip.model.Task;
import kz.aigulsharip.repository.TasksRepositories;
import kz.aigulsharip.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class MainController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String tasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks.html";
    }

    @GetMapping(value = "/addTask")
    public String addTaskPage() {
        return "addTask.html";
    }

    @PostMapping(value = "/addTask")
    public String addTask(@RequestParam(name = "name") String name,
                          @RequestParam(name = "deadline") Date deadline) {
        taskService.addTask(name, deadline);
        return "redirect:/tasks/";

    }

    @GetMapping(value = "/details/{id}.html")
    public String readItem(Model model, @PathVariable(name = "id") Long id) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "taskDetails.html";
    }

    @PostMapping(value = "/saveTask")
    public String saveTask(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "deadline") Date deadline) {

        taskService.updateTask(id, name, deadline);
        return "redirect:/tasks/";
    }

    @PostMapping(value = "/deleteTask")
    public String deleteItem(@RequestParam(name = "id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks/";

    }

    @PostMapping(value = "/completeTask")
    public String makeTaskComplete(@RequestParam(name = "id") Long id) {
        taskService.makeTaskCompleted(id);
        return "redirect:/tasks/";
    }


}
