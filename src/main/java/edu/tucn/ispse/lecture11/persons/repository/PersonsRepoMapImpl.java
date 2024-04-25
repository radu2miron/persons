/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tucn.ispse.lecture11.persons.repository;

import edu.tucn.ispse.lecture11.persons.model.Person;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author radu
 */
public class PersonsRepoMapImpl implements PersonsRepo {

    private static PersonsRepo INSTANCE = new PersonsRepoMapImpl();
    private Map<String, Person> persons = new HashMap<>();

    private PersonsRepoMapImpl(){
    }
    
    @Override
    public void create(Person person) {
        persons.put(person.idNumber(), person);
    }

    @Override
    public Person read(String idNumber) {
        Person person = persons.get(idNumber);

        if (person != null) {
            return person;
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }

    @Override
    public void update(Person person) {
        if (persons.containsKey(person.idNumber())) {
            persons.put(person.idNumber(), person);
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }

    @Override
    public void delete(String idNumber) {
        if (persons.containsKey(idNumber)) {
            persons.remove(idNumber);
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }
    
    public static PersonsRepo getINSTANCE() {
        return INSTANCE;
    }
}

