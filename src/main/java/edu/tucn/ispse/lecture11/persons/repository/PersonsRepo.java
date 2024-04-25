/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tucn.ispse.lecture11.persons.repository;

import edu.tucn.ispse.lecture11.persons.model.Person;

/**
 *
 * @author radu
 */
public interface PersonsRepo {
    void create(Person person);
    Person read(String idNumber);
    void update(Person person);
    void delete(String idNumber);
}
