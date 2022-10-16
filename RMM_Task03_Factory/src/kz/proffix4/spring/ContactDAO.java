package kz.proffix4.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Contact
 *
 */
public class ContactDAO implements IContactDAO {

    private DataSource dataSource;
    
    

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Contact person) { // Реализация вставки новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO TELEPHONS (NAME, SURNAME, TELEPHONE_NUMBER) VALUES(?,?,?)",
                new Object[]{person.getFirstName(), person.getLastName(), person.getTelephone()});
    }
    

    @Override
    public void append(String firstName, String lastName, String telephone) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO TELEPHONS (NAME, SURNAME, TELEPHONE_NUMBER) VALUES(?,?,?)", 
                new Object[]{firstName, lastName, telephone});
    }

    @Override
    public void deleteByLastName(String lastname) {  // Реализация удаления записей по фамилии
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM TELEPHONS WHERE SURNAME LIKE ?", new Object[]{'%' + lastname + '%'});
    }
    @Override
    public void deleteByName(String firstName) {  // Реализация удаления записей по фамилии
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM TELEPHONS WHERE NAME LIKE ?", new Object[]{'%' + firstName + '%'});
    }
    
    @Override
    public void deleteByTelephone(String telephone) {  // Реализация удаления записей по фамилии
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM TELEPHONS WHERE TELEPHONE_NUMBER LIKE ?", new Object[]{'%' + telephone + '%'});
    }

    @Override
    public void delete(final String firstName, final String lastName) {  // Реализация удаления записей с указанным именем и фамилией
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from TELEPHONS where NAME= ? AND SURNAME = ?", new Object[]{firstName, lastName});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from TELEPHONS");
    }
    
    @Override
    public void updateByTelephone(String oldNumber, String newNumber) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE TELEPHONS SET TELEPHONE_NUMBER = ? WHERE TELEPHONE_NUMBER = ?", new Object[]{newNumber, oldNumber});
    }
    @Override
    public void updateByFirstName(String oldFirstName, String newFirstName) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE TELEPHONS SET NAME = ? WHERE NAME = ?", new Object[]{newFirstName, oldFirstName});
    }

    @Override
    public void updateByLastName(String oldLastName, String newLastName) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE TELEPHONS SET SURNAME = ? WHERE SURNAME = ?", new Object[]{newLastName, oldLastName});
    }

    @Override
    public List<Contact> findByTelephone(String telephoneNumber) { // Реализация роиска записи с заданным возрастом
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Contact> person = jt.query("SELECT * FROM TELEPHONS WHERE TELEPHONE_NUMBER LIKE ?",
                new Object[]{"%" + telephoneNumber + "%"}, new ContactRowMapper());
        return person;
    }
    
    @Override
    public List<Contact> findByLastName(String lastName) { // Реализация роиска записи с заданным возрастом
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Contact> person = jt.query("SELECT * FROM TELEPHONS WHERE SURNAME LIKE ?",
                new Object[]{"%" + lastName + "%"}, new ContactRowMapper());
        return person;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {  // Реализация поиска записей по имени
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM TELEPHONS WHERE NAME LIKE ?";
        List<Contact> persons = jt.query(sql, new Object[]{"'%'" + firstName + "'%'"}, new ContactRowMapper());
        return persons;
    }
   

    @Override
    public List<Contact> select(String firstName, String lastName) {  // Реализация получения записей с заданным именем и фамилией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from TELEPHONS where NAME = ? AND SURNAME= ?",
                new Object[]{firstName, lastName}, new ContactRowMapper());
    }

    @Override
    public List<Contact> selectAll() {  // Реализация получения всех записей
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from TELEPHONS", new ContactRowMapper());
    }

}
