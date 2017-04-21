package services;

import jpa.Task;

import java.util.List;

public interface TaskPersistenceService {
    void saveTask(Task t);

    List<Task> fetchAllTasks();
    
    /*
    void saveTask1(Task t);
     
    List<Task> fetchAllTasks1();
    */
}
