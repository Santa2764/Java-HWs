package com.company.models;

import com.company.print.FullBookPrintable;
import com.company.print.IBookPrintable;

import java.util.Scanner;

public class Book extends Publication {
    private String author;
    private String genre;
    private int pages;
    private IBookPrintable bookPrintable;

    public Book() {
        this("", "", "", 0, new FullBookPrintable());
    }

    public Book(
            String title,
            String author,
            String genre,
            int pages) {
        this(title, author, genre, pages, new FullBookPrintable());
    }

    public Book(
            String title,
            String author,
            String genre,
            int pages,
            IBookPrintable bookPrintable) {
        super(title);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        setPrintType(bookPrintable);
    }

    @Override
    public void print() {
        bookPrintable.print(this);
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input title: ");
        this.title = scanner.nextLine();

        System.out.print("Input author: ");
        this.author = scanner.nextLine();

        System.out.print("Input genre: ");
        this.genre = scanner.nextLine();

        System.out.print("Input pages: ");
        this.pages = scanner.nextInt();

        scanner.nextLine();
    }

    @Override
    public boolean hasAuthor(String author) {
        return this.author.contains(author);
    }

    public void setPrintType(IBookPrintable bookPrintable) {
        if (bookPrintable != null) {
            this.bookPrintable = bookPrintable;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
