package common;

public class Phonebook {
    private String id;
    private String name;
    private String content;
    
    // Constructor for the Phonebook class
    public Phonebook(String id, String name, String content) {
            super();
            this.id = id;
            this.name = name;
            this.content = content;
    }
    
    // Getter and setter methods for all the fields.
    // Though you would not be using the setters for this example,
    // it might be useful later.
    public String getId() {
            return id;
    }
    public void setId(String id) {
            this.id = id;
    }
    public String getName() {
            return name;
    }
    public void setName(String name) {
            this.name = name;
    }
    public String getContent() {
            return content;
    }
    public void setContent(String content) {
            this.content = content;
    }
    
}
