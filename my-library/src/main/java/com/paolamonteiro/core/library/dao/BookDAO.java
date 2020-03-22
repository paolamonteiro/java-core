package com.paolamonteiro.core.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.paolamonteiro.core.library.exceptions.LibraryException;
import com.paolamonteiro.core.library.models.Book;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class BookDAO {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE = "src/main/java/resources/Books.json";

    public void save(List<Book> books) {
        File file = new File(FILE);
        if (file.exists() && !file.isDirectory()) {
            String json = gson.toJson(books);
            try (FileWriter writer = new FileWriter(FILE)) {
                writer.write(json);
            } catch (IOException e) {
                throw new LibraryException("Error saving data.");
            }
        }
    }

    public List<Book> getAll() {
        List<Book> booksList;
        Type listType = new TypeToken<List<Book>>(){}.getType();
        try (java.io.FileReader fileReader = new FileReader(FILE)) {
            booksList = gson.fromJson(fileReader, listType);
            if(booksList == null || booksList.size() == 0){
                return Collections.emptyList();
            }
        } catch (IOException e) {
            throw new LibraryException("Error accessing data.");
        }
        return booksList;
    }
}
