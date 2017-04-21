package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
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
    
    //can wipe this block if needed
    /*
    @Id
    @GeneratedValue
    private Long id2;
    
    @Column(nullable = false)
    private String contents1;
    
    public Long getId1() {
        return id2;
    }

    public void setId1(Long id2) {
        this.id2 = id2;
    }
    
    public String getContents1() {
        return contents1;
    }
    
    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }
    */
}
