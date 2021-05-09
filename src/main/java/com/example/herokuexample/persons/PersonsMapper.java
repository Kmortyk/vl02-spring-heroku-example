package com.example.herokuexample.persons;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonsMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person p = new Person();
        p.id = resultSet.getInt("id");
        p.name = resultSet.getString("name");
        return p;
    }
}
