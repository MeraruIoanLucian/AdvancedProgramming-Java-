/**
 * Represents a student, which is a person with a registration number.
 */
public class Student extends Person {
    private String registrationNumber;

    /**
     * Constructs a student.
     * @param name the student's name
     * @param dateOfBirth the student's date of birth
     * @param registrationNumber the student's registration number
     */
    public Student(String name, String dateOfBirth, String registrationNumber) {
        super(name, dateOfBirth);
        this.registrationNumber = registrationNumber;
    }

    /**
     * Returns the registration number.
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the registration number.
     * @param registrationNumber the registration number to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Student[name=" + getName() + ", dateOfBirth=" + getDateOfBirth() +
                ", registrationNumber=" + registrationNumber + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return registrationNumber.equals(other.registrationNumber);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + registrationNumber.hashCode();
    }
}