package com.company.models;

import com.company.print.FullAlmanacPrintable;
import com.company.print.IAlmanacPrintable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Almanac extends Publication {
    private Book[] books;
    private IAlmanacPrintable almanacPrintable;

    public Almanac() {
        this("", new Book[] {}, new FullAlmanacPrintable());
    }

    public Almanac(String title, Book[] books) {
        this(title, books, new FullAlmanacPrintable());
    }

    public Almanac(
            String title,
            Book[] books,
            IAlmanacPrintable almanacPrintable) {
        super(title);
        setBooks(books);
        setPrintType(almanacPrintable);
    }

    @Override
    public void print() {
        almanacPrintable.print(this);
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input title: ");
        this.title = scanner.nextLine();
    }

    @Override
    public boolean hasAuthor(String author) {
        for (Book book : books) {
            if (book.hasAuthor(author)) {
                return true;
            }
        }
        return false;
    }

    public void setPrintType(IAlmanacPrintable almanacPrintable) {
        if (almanacPrintable != null) {
            this.almanacPrintable = almanacPrintable;
        }
    }

    public Book[] getBooks() {
        return Arrays.copyOf(books, books.length);
    }

    public void setBooks(final Book[] books) {
        if (books != null) {
            this.books = Arrays.copyOf(books, books.length);
        }
    }
}
