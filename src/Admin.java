public class Admin extends Employee {
    private String password;

    public Admin(String name, String email, String dateOfBirth, double salary, String password) {
        super(name, email, dateOfBirth, salary);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "----------- Admin info -----------\n" +
               "Admin name: " + name + "\n" +
               "Admin e-mail: " + email + "\n" +
               "Admin date of birth: " + dateOfBirth;
    }
}
