package com.example;

import jakarta.enterprise.context.Dependent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Dependent
public class ActionsService {

    void handleRunAction(String name) throws MalformedURLException {
        if (name == null) {
            System.out.printf("The 'name' parameter must not be empty !%n");
            return;
        }

        URL url = new URL("https://raw.githubusercontent.com/sylvan0s/jsonPath/master/files/example.json");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("This will be used for the run command with the name : %s%n", name);
    }

    void handleDeleteAction(String dataset) {
        if (dataset == null) {
            System.out.printf("The 'name' parameter must not be empty !%n");
            return;
        }

        System.out.printf("This will be used to delete the dataset %s%n", dataset);
    }

    void handleNotAnAction() {
        System.out.printf("This is not an expected command !%n");
    }

}
