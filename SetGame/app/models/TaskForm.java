package models;

import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;

public class TaskForm {

    @Required
    @MaxLength(value = 11)
    @MinLength(value = 2) 
    @Pattern(value = "[\\S]+", message = "Your input must have no white space.")
    private String contents;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    /*
    @Required
    private String contents1;

    public String getContents1() {
        return contents1;
    }

    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }
    */
}
