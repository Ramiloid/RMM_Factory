package kz.proffix4.spring;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Contact из считанной записи таблицы БД
 *
 */
public class ContactRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        ContactResultSetExtractor extractor = new ContactResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class ContactResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getInt("id"));
            contact.setFirstName(rs.getString("name"));
            contact.setLastName(rs.getString("surname"));
            contact.setTelephone(rs.getString("telephone_number"));
            return contact;
        }
    }
}
