package com.company.print;

import com.company.models.Almanac;
import com.company.models.Book;

public class FullAlmanacPrintable implements IAlmanacPrintable {

    @Override
    public void print(Almanac almanac) {
        System.out.println("-------- Almanac info --------");
        System.out.println("Title: " + almanac.getTitle());

        for (Book book : almanac.getBooks()) {
            book.setPrintType(new FullBookPrintable());
            book.print();
        }
    }
}
