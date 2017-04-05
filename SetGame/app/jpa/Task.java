package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
import play.db.ebean.Model;

@Entity
public class Task extends Model {
public class Task {
 
     @Id

    @GeneratedValue
    private Long id;

    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    public String getContents() {
        return contents;
    }
 
    public void setContents(String contents) {
        this.contents = contents;
    }
}    