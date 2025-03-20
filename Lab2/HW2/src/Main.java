/**
 * The main class to test the project allocation system.
 */
public class Main {
    /**
     * Main method.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Problem problem = new Problem();
        Teacher t1 = new Teacher("Dr. Popa", "1970-01-01");
        Teacher t2 = new Teacher("Dr. Ioan", "1965-05-15");
        problem.addTeacher(t1);
        problem.addTeacher(t2);
        Student s1 = new Student("Ana", "2000-03-10", "123");
        Student s2 = new Student("Bogdan", "1999-07-22", "321");
        Student s3 = new Student("Cristi", "2001-11-05", "111");
        problem.addStudent(s1);
        problem.addStudent(s2);
        problem.addStudent(s3);
        Project p1 = new Project("AI Research", "Project on AI", t1);
        Project p2 = new Project("Web App", "Project on web development", t2);
        problem.addProject(p1);
        problem.addProject(p2);
        t1.addProposedProject(p1);
        t2.addProposedProject(p2);
        problem.allocateProjects();
        System.out.println(problem);
    }
}