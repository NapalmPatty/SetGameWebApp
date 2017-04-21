package models;

import play.data.validation.Constraints.Required;

public class TaskForm {

    @Required
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
