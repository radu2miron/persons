/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.tucn.ispse.lecture11.persons;

import com.formdev.flatlaf.FlatDarkLaf;
import edu.tucn.ispse.lecture11.persons.model.Address;
import edu.tucn.ispse.lecture11.persons.model.Person;
import edu.tucn.ispse.lecture11.persons.repository.PersonsRepo;
import edu.tucn.ispse.lecture11.persons.repository.PersonsRepoJsonFileImpl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.UIManager;
import javax.swing.text.DateFormatter;

/**
 *
 * @author radu
 */
public class Persons {

    public static void main(String[] args) throws ParseException {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}
