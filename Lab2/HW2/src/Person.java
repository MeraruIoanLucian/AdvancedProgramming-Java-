/**
 * Represents a person with a name and a date of birth.
 */
public class Person {
    private String name;
    private String dateOfBirth;

    /**
     * Constructs a person.
     * @param name the person's name
     * @param dateOfBirth the person's date of birth
     */
    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the person's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the person's name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the person's date of birth.
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the person's date of birth.
     * @param dateOfBirth the date of birth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return name.equals(other.name) && dateOfBirth.equals(other.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + dateOfBirth.hashCode();
    }
}