enum ProjectType {
    THEORETICAL,
    PRACTICAL
}

class Student {
    private int id;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private String name;
    private String email;
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String toString() {
        return "Student: id=" + id + ", name=" + name + ", email=" + email + ".";
    }
}

class Project {
    private String title;
    private String description;
    private ProjectType projectType;
    public Project(String title, String description, ProjectType projectType) {
        this.title = title;
        this.description = description;
        this.projectType = projectType;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ProjectType getProjectType() { return projectType; }
    public void setProjectType(ProjectType projectType) { this.projectType = projectType; }
    public String toString() {
        return "Project: title=" + title + ", description=" + description + ", projectType=" + projectType + ".";
    }
}

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "`Ionut", "ioan.lucian.meraru@gmail.com");
        Project project = new Project("OOP si java", "Exemplu la lab de java", ProjectType.THEORETICAL);

        System.out.println(student.toString());
        System.out.println(project.toString());
    }
}