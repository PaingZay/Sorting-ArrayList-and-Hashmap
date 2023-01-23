package club;

public class Person {

    private String surname;
    private String firstName;
    private String secondName;

    public Person (String surname, String firstName, String secondName) {
        this.surname = surname;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString () {
        String fullName = firstName;
        if (secondName != null) {
            fullName += " " + secondName;
        }
        fullName += " " + surname;
        return fullName;
    }

    public void show () {
        System.out.println (this);
    }
}
