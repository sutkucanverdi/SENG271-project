import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
    protected String name;
    protected String email;
    // input dosyalarından gelen ham tarih (02.10.2000 gibi)
    protected String dateOfBirth;

    // users.txt'deki tarih formatı
    private static final SimpleDateFormat DOB_INPUT_FORMAT =
            new SimpleDateFormat("dd.MM.yyyy");

    public Person(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Ham string tarihi döndürür (gerekirse)
     */
    public String getRawDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * ÖDEVDEKİ gibi Date.toString() formatına çevirilmiş tarih.
     * Örnek: Mon Oct 02 00:00:00 EEST 2000
     */
    protected String getFormattedDateOfBirth() {
        try {
            Date d = DOB_INPUT_FORMAT.parse(dateOfBirth);
            return d.toString();   // sistem timezone’una göre yazacak
        } catch (ParseException e) {
            // parse edemezsek en azından ham halini yazalım
            return dateOfBirth;
        }
    }

    @Override
    public String toString() {
        // Bu genel bir fallback; asıl ödev çıktıları Customer/Admin toString ile gelecek.
        return "Name: " + name +
               " E-mail: " + email +
               " Date of Birth: " + getFormattedDateOfBirth();
    }
}
