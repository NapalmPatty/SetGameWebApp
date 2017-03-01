package controllers;

import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;
import models.Task;
import views.html.*;

public class Application extends Controller {

	@Transactional
    public static Result index() {
        return ok(index.render("Pink Bunny Slippers", play.data.Form.form(models.Task.class)));   //takes the GET tasks and puts them on the base page
    }                           //displays the title

	
    public static Result addTask() {
    	play.data.Form<models.Task> form = play.data.Form.form(models.Task.class).bindFromRequest();   //adds a form to the base page to create data
    	models.Task task = form.get();
    	task.save();                   //Saves the data until SBT shuts down
    	return redirect(routes.Application.index());
    }
    
    public static Result getTasks() {
    	java.util.List<models.Task> tasks = new play.db.ebean.Model.Finder(String.class, models.Task.class).all();
    	return ok(play.libs.Json.toJson(tasks));   //Returns the data as raw data to be translated by javascript
    }
}
