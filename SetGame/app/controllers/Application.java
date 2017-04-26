package controllers;

import jpa.Task;
import models.TaskForm;
import services.TaskPersistenceService;
import views.html.index;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class Application extends Controller {

	private static final Logger applogger = LoggerFactory.getLogger(Application.class);
	
    @Inject
    private TaskPersistenceService taskPersist;

    public Result index() {
    	applogger.info("Shaved Brown Fox Moccasins");
        return ok(index.render("Fuzzy Pink Bunny Slippers", Form.form(TaskForm.class)));
    }
    
    public Result addTask() {
        Form<TaskForm> form = Form.form(TaskForm.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(index.render("Fuzzy Pink Bunny Slippers", form));
        }

        Task task = new Task();
        task.setContents(form.get().getContents());
        taskPersist.saveTask(task);
        return redirect(routes.Application.index());
    }
    
    public Result getTasks() {
        List<Task> tasks = taskPersist.fetchAllTasks();
        return ok(play.libs.Json.toJson(tasks));
    }
    
    /*
    public Result index1() {
        return ok(index.render("Totally not stolen from Nate but was copypasted and now it somehow works Jesus Christ Nate I don't fucking know", Form.form(TaskForm.class)));
    }
    public Result addTask1() {
        Form<TaskForm> page2form = Form.form(TaskForm.class).bindFromRequest();
        if (page2form.hasErrors()) {
            return badRequest(index.render("Totally not stolen from Nateeeee", page2form));
        }

        Task page2 = new Task();
        page2.setContents(page2form.get().getContents1());
        taskPersist.saveTask(page2);
        return redirect(routes.Application.index());
    }
    public Result getTasks1() {
        List<Task> page3 = taskPersist.fetchAllTasks();
        return ok(play.libs.Json.toJson(page3));
    }
    */
}
