package kz.proffix4.spring;

import javax.sql.DataSource;
import java.util.List;

/**
 * Интерфейс работы с таблицой Contact
 *
 */
public interface IContactDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Contact person); // Вставка новой записи
    void append(String firstName, String lastName, String telephoneNumber); // Добавление новой записи
    void deleteByLastName(String lastName); // Удаление записи по фамилии
    void deleteByName(String firstName);//Удаление записи по имени
    void deleteByTelephone(String telephoneNumber);//Удаление записи по номеру
    void delete(String firstName, String lastName); // Удаление записи с указанным именем и фамилией
    void deleteAll(); // Удаление всех запией
    void updateByLastName(String oldLastName, String newLastName); // Изменение записей в таблице
    void updateByFirstName(String oldFirstName, String newFirstName); // Изменение записей в таблице
    void updateByTelephone(String oldTelephoneNumber, String newTelephoneNumber); // Изменение записей в таблице
    List<Contact> findByTelephone(String telephoneNumber); // Получение записи с заданным телефоном
    List<Contact> findByLastName(String LastName); // Получение записи с заданной фамилией
    List<Contact> findByFirstName(String firstName); // Получение записей с заданным именем 
    List<Contact> select(String firstName, String lastName); // Получение записей с заданным именем и фамилией
    List<Contact> selectAll(); // Получение всех записей
}
