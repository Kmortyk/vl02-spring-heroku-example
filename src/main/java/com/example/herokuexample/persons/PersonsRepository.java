package com.example.herokuexample.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PersonsMapper personsMapper = new PersonsMapper();

    public void insertPerson(String name) {
        jdbcTemplate.update("INSERT INTO persons (name) VALUES (?)", name);
    }

    public Person selectPerson(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM persons WHERE id = ?", personsMapper, id);
    }

    public List<Person> selectAllPersons() { // debug
        return jdbcTemplate.query("SELECT * FROM persons", personsMapper);
    }
}
