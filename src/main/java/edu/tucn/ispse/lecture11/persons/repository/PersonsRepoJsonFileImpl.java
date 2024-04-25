/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tucn.ispse.lecture11.persons.repository;

import com.google.gson.Gson;
import edu.tucn.ispse.lecture11.persons.model.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author radu
 */
public class PersonsRepoJsonFileImpl implements PersonsRepo {

    private static final File REPO = new File("/tmp/persons");
    private static final Gson GSON = new Gson();
    private static final PersonsRepo INSTANCE = new PersonsRepoJsonFileImpl();

    private PersonsRepoJsonFileImpl() {
        if (!REPO.exists()) {
            REPO.mkdirs(); // create repo dir if it doesn't exist
        }
    }

    @Override
    public void create(Person person) {

        try (Writer writer = new FileWriter(new File(REPO, person.idNumber() + ".json"))) {
            GSON.toJson(person, writer);
            writer.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Person read(String idNumber) {
        File jsonFile = new File(REPO, idNumber + ".json");

        if (jsonFile.exists()) {
            try {
                return GSON.fromJson(new FileReader(jsonFile), Person.class);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }

    @Override
    public void update(Person person) {
        File jsonFile = new File(REPO, person.idNumber() + ".json");

        if (jsonFile.exists()) {
            try (Writer writer = new FileWriter(new File(REPO, person.idNumber() + ".json"))) {
                GSON.toJson(person, writer);
                writer.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }

    @Override
    public void delete(String idNumber) {
        File jsonFile = new File(REPO, idNumber + ".json");

        if (jsonFile.exists()) {
            jsonFile.delete();
        } else {
            throw new IllegalArgumentException("Person doesn't exist");
        }
    }

    public static PersonsRepo getINSTANCE() {
        return INSTANCE;
    }
}
