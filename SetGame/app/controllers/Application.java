package controllers;

import play.*;
import play.mvc.*;
import models.Task;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Pink Bunny Slippers"));
    }

    public static Result addTask() {
    	play.data.Form<models.Task> form = play.data.Form.form(models.Task.class).bindFromRequest();
    	models.Task task = form.get();
    	task.save();
    	return redirect(routes.Application.index());
    }
}
