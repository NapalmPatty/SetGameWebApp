package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task extends Model {
	
	@Id
	public String id;
	                          //provides the IDs for Task
	public String contents;
	
}
