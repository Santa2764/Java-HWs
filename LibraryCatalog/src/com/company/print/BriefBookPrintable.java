package com.company.print;

import com.company.models.Book;

public class BriefBookPrintable implements IBookPrintable {
    @Override
    public void print(Book book) {
        System.out.println("-------- Book info --------");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
    }
}
