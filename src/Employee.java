public abstract class Employee extends Person {
    protected double salary;

    public Employee(String name, String email, String dateOfBirth, double salary) {
        super(name, email, dateOfBirth); // İsim, email vs. Person'a gönder
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}