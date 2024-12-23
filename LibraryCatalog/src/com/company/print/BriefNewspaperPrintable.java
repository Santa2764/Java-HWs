package com.company.print;

import com.company.models.Newspaper;

public class BriefNewspaperPrintable implements INewspaperPrintable {
    @Override
    public void print(Newspaper newspaper) {
        System.out.println("-------- Newspaper info --------");
        System.out.println("Title: " + newspaper.getTitle());
        System.out.println("Publication date: " + newspaper.getPublicationDate().toString());
    }
}
