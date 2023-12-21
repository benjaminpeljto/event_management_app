package ba.ibu.edu.web_engineering_project.core.model.embedded;

public class Organizer {
    private String id;
    private String name;
    private String email;

    public Organizer(){}

    public Organizer(String name) {
        this.name = name;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
