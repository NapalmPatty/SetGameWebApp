package services;

import jpa.Task;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
//import javax.inject.Inject;

@Named
public class TaskPersistenceServiceImpl implements TaskPersistenceService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void saveTask(Task task) {
        if (task.getContents() == null) {
            throw new IllegalArgumentException("Contents must not be blank");
        }
        if (task.getId() != null) {
        	throw new IllegalArgumentException("Id shouldn't be null");
        }
        if (task.getContents().length() < 2) {
        	throw new ValidationException("Contents contain inadequate characters");
        }
        if (task.getContents().length() > 11) {
        	throw new ValidationException("Contents contain excess characters");
        }
        em.persist(task);
    }

    @Override
    public List<Task> fetchAllTasks() {
        return em.createQuery("from Task", Task.class).getResultList();
    }
}
