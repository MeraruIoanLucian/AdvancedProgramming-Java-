import java.util.ArrayList;
import java.util.List;

/**
 * Represents a teacher, which is a person who proposes projects.
 */
public class Teacher extends Person {
    private List<Project> proposedProjects;

    /**
     * Constructs a teacher.
     * @param name the teacher's name
     * @param dateOfBirth the teacher's date of birth
     */
    public Teacher(String name, String dateOfBirth) {
        super(name, dateOfBirth);
        proposedProjects = new ArrayList<>();
    }

    /**
     * Returns the list of proposed projects.
     * @return the list of projects
     */
    public List<Project> getProposedProjects() {
        return proposedProjects;
    }

    /**
     * Adds a project to the teacher's list if it is not already present.
     * @param project the project to add
     */
    public void addProposedProject(Project project) {
        if (!proposedProjects.contains(project)) {
            proposedProjects.add(project);
        }
    }

    @Override
    public String toString() {
        return "Teacher[name=" + getName() + ", dateOfBirth=" + getDateOfBirth() +
                ", proposedProjects=" + proposedProjects + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        return obj instanceof Teacher;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}