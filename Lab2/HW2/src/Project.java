/**
 * Represents a project proposed by a teacher.
 */
public class Project {
    private String title;
    private String description;
    private Teacher proposedBy;
    private Student allocatedStudent;

    /**
     * Constructs a project.
     * @param title the project's title
     * @param description the project's description
     * @param proposedBy the teacher who proposed the project
     */
    public Project(String title, String description, Teacher proposedBy) {
        this.title = title;
        this.description = description;
        this.proposedBy = proposedBy;
        allocatedStudent = null;
    }

    /**
     * Returns the project's title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the project's title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the project's description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the project's description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the teacher who proposed the project.
     * @return the proposing teacher
     */
    public Teacher getProposedBy() {
        return proposedBy;
    }

    /**
     * Sets the teacher who proposed the project.
     * @param proposedBy the teacher to set
     */
    public void setProposedBy(Teacher proposedBy) {
        this.proposedBy = proposedBy;
    }

    /**
     * Returns the allocated student, if any.
     * @return the allocated student or null
     */
    public Student getAllocatedStudent() {
        return allocatedStudent;
    }

    /**
     * Allocates a student to the project.
     * @param allocatedStudent the student to allocate
     */
    public void setAllocatedStudent(Student allocatedStudent) {
        this.allocatedStudent = allocatedStudent;
    }

    @Override
    public String toString() {
        String studentStr = allocatedStudent != null ? allocatedStudent.getName() : "none";
        return "Project[title=" + title + ", description=" + description +
                ", proposedBy=" + proposedBy.getName() + ", allocatedStudent=" + studentStr + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project other = (Project) obj;
        return title.equals(other.title) && proposedBy.equals(other.proposedBy);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + proposedBy.hashCode();
    }
}