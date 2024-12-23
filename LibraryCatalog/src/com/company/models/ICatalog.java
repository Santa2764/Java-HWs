package com.company.models;

public interface ICatalog {
    void initialize();
    void add(Publication publication);
    void addRandom();
    Publication deleteByTitle(String title);
    void show();
    Publication[] searchByTitle(String title);
    Publication[] searchByAuthor(String author);
}
