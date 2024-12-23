package com.company.tests;

import java.time.LocalDate;
import java.util.Random;

import com.company.models.Almanac;
import com.company.models.Book;
import com.company.models.Newspaper;
import com.company.models.Publication;

public class TaskFactory {
    public static Book getBook() {
        String title = TestUtils.getRandomAnyTitle();
        String author = TestUtils.getRandomFirstName() + " " + TestUtils.getRandomLastName();
        String genre = TestUtils.getRandomBookGenre();
        int pages = TestUtils.getRandomInteger(50, 2000);
        return new Book(title, author, genre, pages);
    }

    public static Newspaper getNewspaper() {
        String title = TestUtils.getRandomAnyTitle();
        LocalDate publicationDate = TestUtils.getRandomDate();
        String[] mainHeadlines = TestUtils.getRandomAnyTitles();
        return new Newspaper(title, publicationDate, mainHeadlines);
    }

    public static Almanac getAlmanac() {
        String title = TestUtils.getRandomAnyTitle();

        Random rand = new Random();
        int size = rand.nextInt(2, 7);
        Book[] books = new Book[size];
        for (int i = 0; i < size; i++) {
            books[i] = getBook();
        }

        return new Almanac(title, books);
    }

    public static Publication getRandomPublication() {
        int num = TestUtils.getRandomInteger(1, 3);
        if (num == 1) {
            return getBook();
        }
        else if (num == 2) {
            return getNewspaper();
        }
        else {
            return getAlmanac();
        }
    }

    public static Publication[] getRandomPublications() {
        int count = TestUtils.getRandomInteger(2, 5);
        Publication[] randPublications = new Publication[count];

        for (int i = 0; i < count; i++) {
            randPublications[i] = getRandomPublication();
        }

        return randPublications;
    }
}
