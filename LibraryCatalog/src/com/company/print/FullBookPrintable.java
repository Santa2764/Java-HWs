package com.company.print;

import com.company.models.Book;

public class FullBookPrintable implements IBookPrintable {
    @Override
    public void print(Book book) {
        System.out.println("-------- Book info --------");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Pages: " + book.getPages());
    }
}
