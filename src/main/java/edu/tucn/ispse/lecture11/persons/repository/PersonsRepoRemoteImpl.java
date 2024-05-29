/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tucn.ispse.lecture11.persons.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.tucn.ispse.lecture11.persons.model.Person;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author radu
 */
public class PersonsRepoRemoteImpl implements PersonsRepo {

    private static final String BASE_URL = "http://localhost:8082/persons%s";
    private static final PersonsRepo INSTANCE = new PersonsRepoRemoteImpl();
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();;

    private PersonsRepoRemoteImpl() {
    }

    public static PersonsRepo getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void create(Person person) {
        try {
            String personJson = GSON.toJson(person);
            System.out.println(personJson);
            URI target = new URI(String.format(BASE_URL, ""));
            HttpRequest request = HttpRequest.newBuilder(target)
                    .POST(HttpRequest.BodyPublishers.ofString(personJson))
                    .header("Content-Type", "application/json").build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Invalid response: " + response.statusCode() + ": " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Person read(String idNumber) {
        Person person = null;

        try {
            URI target;
            target = new URI(String.format(BASE_URL, "/" + idNumber));
            HttpRequest request = HttpRequest.newBuilder(target).GET().build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                person = GSON.fromJson(response.body(), Person.class);
            } else {
                throw new RuntimeException("Invalid response: " + response.statusCode() + ": " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

        return person;
    }

    @Override
    public void update(Person person) {
        try {
            URI target = new URI(String.format(BASE_URL, "/" + person.idNumber()));
            HttpRequest request = HttpRequest.newBuilder(target)
                    .PUT(HttpRequest.BodyPublishers.ofString(GSON.toJson(person)))
                    .header("Content-Type", "application/json").build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Invalid response: " + response.statusCode() + ": " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String idNumber) {
        try {
            URI target = new URI(String.format(BASE_URL, "/" + idNumber));
            HttpRequest request = HttpRequest.newBuilder(target)
                    .DELETE().build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Invalid response: " + response.statusCode() + ": " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Set<String> findAllIds() {
        Set<String> ids = null;

        try {
            URI target = new URI(String.format(BASE_URL, "/findAllIds"));
            HttpRequest request = HttpRequest.newBuilder(target).GET().build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Type setType = new TypeToken<Set<String>>() {
                }.getType();
                ids = GSON.fromJson(response.body(), setType);
            } else {
                throw new RuntimeException("Invalid response: " + response.statusCode() + ": " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

        return ids;
    }

}
