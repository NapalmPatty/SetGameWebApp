package services;

import java.util.List;

import jpa.Task;

public interface TaskPersistenceService {
    void saveTask(Task t);

    List<Task> fetchAllTasks();
}