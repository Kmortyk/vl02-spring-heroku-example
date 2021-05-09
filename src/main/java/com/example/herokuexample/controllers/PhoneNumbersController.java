package com.example.herokuexample.controllers;

import com.example.herokuexample.phonenumbers.PhoneBookItem;
import com.example.herokuexample.phonenumbers.PhoneNumbersRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("phone-numbers")
public class PhoneNumbersController {

    @Autowired
    PhoneNumbersRepository phoneNumbersRepository;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void createPhoneNumber(@RequestBody String jsonParam) {
        try {
            JsonObject obj = JsonParser.parseString(jsonParam).getAsJsonObject();
            long phone = obj.getAsJsonPrimitive("phone").getAsLong();
            int personID = obj.getAsJsonPrimitive("personID").getAsInt();

            phoneNumbersRepository.insertPhoneNumber(phone, personID);
        } catch(JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<PhoneBookItem> getPhoneBook() {
        return phoneNumbersRepository.getPhoneBook();
    }

}
