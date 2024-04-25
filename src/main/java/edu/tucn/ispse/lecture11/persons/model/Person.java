/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tucn.ispse.lecture11.persons.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author radu
 */
public record Person(String idNumber, String firstName, String lastName, Date dateOfBirth, Address address) {
    
}
