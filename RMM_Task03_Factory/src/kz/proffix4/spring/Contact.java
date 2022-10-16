package kz.proffix4.spring;

/**
 * Класс, соответсвующий записи в таблице Contact
 *
 */
public class Contact {

    int id;            // Код записи
    String firstName;  // Имя клиента
    String lastName;   // Фамилия клиента
    String telephoneNumber;           // номер

    public Contact() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.telephoneNumber = "0";
    }

    public Contact(String firstName, String lastName, String telephoneNumber) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephone(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return String.format("Имя=%s, Фамилия=%s, Телефон=%d", firstName, lastName, telephoneNumber);
    }

}
