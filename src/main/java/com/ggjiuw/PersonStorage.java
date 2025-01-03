package com.ggjiuw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PersonStorage {
    static List<Person> persons;

    static {
        try {
            persons = load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // IDEA зробила

    public static void add(Person person) throws IOException {
        persons.add(person);
        save();
    }

    public static String getJson() throws IOException {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(persons);
    }

    public static boolean remove(String by_name) throws IOException {
        if (persons.isEmpty()) {
            return false;
        }

        String[] data = find(by_name);

        if (data != null) {
            persons.remove(Integer.parseInt(data[0]));
        }

        return false;
    }

    public static String[] find(String by_name) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getName().equals(by_name)) {
                return new String[]{String.valueOf(i), person.getName(), String.valueOf(person.getAge())};
            }
        }

        return null;
    }



    private static void save() throws IOException {
        ObjectMapper map = new ObjectMapper();
        String line = map.writeValueAsString(persons);

        Path json_path = Paths.get("persons.json");

        if (!(Files.exists(json_path)))
            Files.createFile(json_path);

        try {
            Files.write(json_path, line.getBytes());
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static ArrayList<Person> load() throws IOException {
        Path json_path = Paths.get("persons.json");
        ObjectMapper map = new ObjectMapper();

        if (Files.exists(json_path)) {
            String json = Files.readAllLines(json_path).get(0);
            return map.readValue(json, map.getTypeFactory().constructCollectionType(ArrayList.class, Person.class));
        }

        Files.createFile(json_path);
        return new ArrayList<>();
    }
}
