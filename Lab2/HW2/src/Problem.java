import java.util.ArrayList;
import java.util.List;

/**
 * Represents the management of students, teachers, and projects.
 */
public class Problem {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Project> projects;

    /**
     * Constructs a new Problem instance.
     */
    public Problem() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        projects = new ArrayList<>();
    }

    /**
     * Adds a student if not already present.
     * @param student the student to add
     * @return true if added, false otherwise
     */
    public boolean addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    /**
     * Adds a teacher if not already present.
     * @param teacher the teacher to add
     * @return true if added, false otherwise
     */
    public boolean addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            return true;
        }
        return false;
    }

    /**
     * Adds a project if not already present.
     * @param project the project to add
     * @return true if added, false otherwise
     */
    public boolean addProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
            return true;
        }
        return false;
    }

    /**
     * Returns an array of all persons (students and teachers).
     * @return an array of Person objects
     */
    public Person[] getAllPersons() {
        List<Person> persons = new ArrayList<>();
        persons.addAll(students);
        persons.addAll(teachers);
        return persons.toArray(new Person[persons.size()]);
    }

    /**
     * Allocates projects to students using a simple greedy algorithm.
     */
    public void allocateProjects() {
        for (Student s : students) {
            for (Project p : projects) {
                if (p.getAllocatedStudent() == null) {
                    p.setAllocatedStudent(s);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        String result = "Students:\n";
        for (Student s : students) {
            result += s + "\n";
        }
        result += "Teachers:\n";
        for (Teacher t : teachers) {
            result += t + "\n";
        }
        result += "Projects:\n";
        for (Project p : projects) {
            result += p + "\n";
        }
        return result;
    }
}