public class Technician extends Employee {
    private boolean isSenior;

    public Technician(String name, String email, String dateOfBirth, double salary, boolean isSenior) {
        super(name, email, dateOfBirth, salary);
        this.isSenior = isSenior;
    }

    public boolean isSenior() {
        return isSenior;
    }

    // Teknisyenlerin özel bir toString formatı istenmemiş, standart Person çıktısını kullanabilirler
}