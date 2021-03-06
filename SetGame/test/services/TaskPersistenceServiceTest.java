package services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import configs.AppConfig;
import configs.TestDataConfig;
import jpa.Task;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;

@ContextConfiguration(classes = {AppConfig.class, TestDataConfig.class})
public class TaskPersistenceServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    
	@Inject
    private TaskPersistenceService taskPersist;

    @Test
    public void emptyListTest() {
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should be empty", list.isEmpty());
    }

    @Test
    public void fetchIfSingleTask() {
    	final Task t = new Task();
        t.setContents("contents");
        taskPersist.saveTask(t);
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should have one element", list.size() == 1);
    }
    
    @Test
    public void fetchIfMultipleTasks() {
    	final Task t = new Task();
        final Task f = new Task();
        t.setContents("contents");
        f.setContents("content");
        taskPersist.saveTask(t);
        taskPersist.saveTask(f);
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should have two elements", list.size() == 2);
    }
    
    @Test
    public void saveValidTaskTest() {
        assertTrue("List should be empty", taskPersist.fetchAllTasks().isEmpty());

        final Task t = new Task();
        t.setContents("contents");
        assertNull("ID should not be set before persist is called", t.getId());
        taskPersist.saveTask(t);
        assertNotNull("ID should be set after persist is called", t.getId());
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should have one element", list.size() == 1);
    }

    @Test
    public void saveBlankTaskTest() {
        try {
            final Task t = new Task();
            taskPersist.saveTask(t);
            fail("This should have failed since contents is blank");
        } catch (IllegalArgumentException ignored) {
        }
    }
    
    @Test
    public void saveNullTaskTest() {
        try {
            taskPersist.saveTask(null);
            fail("This should have failed since Task is null.");
        } catch (IllegalArgumentException ignored) {
        }
    }
    
    @Test
    public void saveEmptyContentsTest() {
        try {
            final Task t = new Task();
            t.setContents("");
            taskPersist.saveTask(t);
            fail("This should have failed since contents are empty.");
        } catch (ValidationException ignored) {
        }
    }

    @Test
    public void enterTooFewCharactersTest() {
    	try {
    		final Task t = new Task();
            t.setContents("x");
            taskPersist.saveTask(t);
            fail("Task is below required characters, it should fail.");
    	} catch (ValidationException ignored) {	
    	}
    }
    
    @Test
    public void enterTooManyCharactersTest() {
    	try {
    		final Task t = new Task();
            t.setContents("xxxxxxxxxxxx");
            taskPersist.saveTask(t);
            fail("Task is above required characters, it should fail.");
    	} catch (ValidationException ignored) {	
    	}
    }
    
    @Test
    public void saveNonBlankIdTaskTest() {
        try {
            final Task t = new Task();
            t.setContents("contents");
            t.setId(1L);
            taskPersist.saveTask(t);
            fail("This should have failed since id is not blank");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void saveExistingTaskTest() {
        final Task t = new Task();
        t.setContents("contents");
        taskPersist.saveTask(t);
        assertNotNull("The ID should be set", t.getId());
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should have one element", list.size() == 1);

        // Attempt to save the same task again, should fail.
        try {
            taskPersist.saveTask(t);
            fail("We shouldn't be able to resave the same item");
        } catch (IllegalArgumentException ignored) {	
        }
    }
}
