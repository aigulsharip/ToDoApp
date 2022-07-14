package kz.aigulsharip.service;

import kz.aigulsharip.model.Task;
import kz.aigulsharip.repository.TasksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class TaskService {


    @Autowired
    TasksRepositories tasksRepositories;

    public List<Task> getAllTasks () {
        return tasksRepositories.findAll();
    }

    public Task getTaskById(Long id) {
        return tasksRepositories.findById(id).orElse(null);
     }

    public void addTask(String name, Date deadline) {
        Task task = new Task();
        task.setName(name );
        task.setDeadlineDate(deadline);
        tasksRepositories.save(task);

    }

    public void updateTask(Long id, String name, Date deadline) {
        Task task = tasksRepositories.findById(id).orElse(null);
        if (task != null) {
            task.setName(name);
            task.setDeadlineDate(deadline);

            tasksRepositories.save(task);

        }
    }

    public void deleteTask(Long id) {
        Task task = tasksRepositories.findById(id).orElse(null);
        if (task != null) {
            tasksRepositories.delete(task);
        }

    }

    public void makeTaskCompleted (Long id) {
        Task task = tasksRepositories.findById(id).orElse(null);
        task.setCompleted(true);
        tasksRepositories.save(task);


    }




}
