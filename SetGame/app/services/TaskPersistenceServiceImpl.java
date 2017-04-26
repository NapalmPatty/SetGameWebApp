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
    
    /*@Inject				//remove in Nate Demo to show blow up with working
    private TaskPersistenceService taskPersist;
	*/

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
       
       /* final List<Task> list = taskPersist.fetchAllTasks();
        String ListArray[] = new String[list.size()]; 
        for (int i = 0 ; i <= 1 ; i++) {					//remove in Nate Demo to show blow up with working
        	if (ListArray[i]==task.getContents()) {
        		throw new IllegalArgumentException("Contents must be unique per entry");
        	}
        }*/
        em.persist(task);
    }

    @Override
    public List<Task> fetchAllTasks() {
        return em.createQuery("from Task", Task.class).getResultList();
    }
    /*
    @Override
    public void saveTask1(Task page2) {
        if (page2.getContents() == null) {
            throw new IllegalArgumentException("Contents must not be blanket");
        }
        em.persist(page2);
    }
    
    @Override
    public List<Task> fetchAllTasks1() {
        return em.createQuery("from Task", Task.class).getResultList();
    }
    */
}
