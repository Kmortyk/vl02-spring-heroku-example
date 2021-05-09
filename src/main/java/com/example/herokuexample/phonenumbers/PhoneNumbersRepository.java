package com.example.herokuexample.phonenumbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneNumbersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertPhoneNumber(Long phoneNumber, int personID) {
        String q = "INSERT INTO phone_numbers (value, person_id) VALUES (?, ?)";

        jdbcTemplate.update(q, phoneNumber, personID);
    }

    public List<PhoneBookItem> getPhoneBook() {
        String q = "" +
                "SELECT phone_numbers.id as id, " +
                "       phone_numbers.value as number, " +
                "       persons.name as name " +
                "FROM phone_numbers LEFT JOIN persons " +
                "ON persons.id = phone_numbers.person_id;";

        List<PhoneBookItem> items = new ArrayList<>();

        try {
            DataSource source = jdbcTemplate.getDataSource();
            if(source == null) {
                System.out.println("data source is null");
                return items; // return empty result
            }

            Connection conn = source.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(q);
            while(resultSet.next()) {
                items.add(new PhoneBookItem(
                        resultSet.getString("name"),
                        resultSet.getLong("number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
