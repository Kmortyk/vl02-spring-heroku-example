package com.example.herokuexample.controllers;

import com.example.herokuexample.persons.Person;
import com.example.herokuexample.persons.PersonsRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonsController {

    @Autowired
    PersonsRepository personsRepository;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void createUser(@RequestBody String jsonParam) {
        try {
            JsonObject obj = JsonParser.parseString(jsonParam).getAsJsonObject();
            String name = obj.getAsJsonPrimitive("name").getAsString();
            personsRepository.insertPerson(name);
        } catch(JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Integer id) {
        return personsRepository.selectPerson(id);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Person> getPersons() {
        return personsRepository.selectAllPersons();
    }
}
