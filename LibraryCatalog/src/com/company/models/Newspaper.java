package com.company.models;

import com.company.print.FullNewspaperPrintable;
import com.company.print.IBookPrintable;
import com.company.print.INewspaperPrintable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Newspaper extends Publication {
    private LocalDate publicationDate;
    private String[] mainHeadlines;
    private INewspaperPrintable newspaperPrintable;

    public Newspaper() {
        this("", null, new String[] {}, new FullNewspaperPrintable());
    }

    public Newspaper(
            String title,
            LocalDate publicationDate,
            String[] mainHeadlines) {
        this(title, publicationDate, mainHeadlines, new FullNewspaperPrintable());
    }

    public Newspaper(
            String title,
            LocalDate publicationDate,
            String[] mainHeadlines,
            INewspaperPrintable newspaperPrintable) {
        super(title);
        this.publicationDate = publicationDate;
        this.mainHeadlines = mainHeadlines;
        setMainHeadlines(mainHeadlines);
        setPrintType(newspaperPrintable);
    }

    @Override
    public void print() {
        newspaperPrintable.print(this);
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input title: ");
        this.title = scanner.nextLine();

        System.out.print("Input your 'day' birth date: ");
        int day = scanner.nextInt();
        System.out.print("Input your 'month' birth date: ");
        int month = scanner.nextInt();
        System.out.print("Input your 'year' birth date: ");
        int year = scanner.nextInt();
        this.publicationDate = LocalDate.of(year, month, day);

        scanner.nextLine();
    }

    @Override
    public boolean hasAuthor(String author) {
        return false;
    }

    public void setPrintType(INewspaperPrintable newspaperPrintable) {
        if (newspaperPrintable != null) {
            this.newspaperPrintable = newspaperPrintable;
        }
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String[] getMainHeadlines() {
        return Arrays.copyOf(mainHeadlines, mainHeadlines.length);
    }

    public void setMainHeadlines(final String[] mainHeadlines) {
        if (mainHeadlines != null) {
            this.mainHeadlines = Arrays.copyOf(mainHeadlines, mainHeadlines.length);
        }
    }
}
