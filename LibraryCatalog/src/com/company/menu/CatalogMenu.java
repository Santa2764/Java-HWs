package com.company.menu;

import com.company.models.*;

import java.util.Scanner;

public class CatalogMenu {
    private final LibraryCatalog catalog;

    public CatalogMenu(LibraryCatalog catalog) {
        this.catalog = catalog;
    }


    public void show() {
        System.out.println("Catalog menu:");
        ShowMenu.show();
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int maxChoice = ShowMenu.getSizeMenu();

        System.out.print("\nPlease enter the number menu: ");
        do {
            choice = scanner.nextInt();
            if (choice < ShowMenu.MIN_MENU_NUM || choice > maxChoice) {
                System.out.println("Invalid choice, need from " + ShowMenu.MIN_MENU_NUM + " to " + maxChoice);
            }
        } while(choice < ShowMenu.MIN_MENU_NUM || choice > maxChoice);

        return choice;
    }

    public boolean execute(int choiceMenu) {
        switch (choiceMenu) {
            case 1:
                showCatalog();
                break;

            case 2:
                addBook();
                break;

            case 3:
                addNewspaper();
                break;

            case 4:
                addAlmanac();
                break;

            case 5:
                addRandomCatalog();
                break;

            case 6:
                deleteByTitle();
                break;

            case 7:
                searchByTitle();
                break;

            case 8:
                searchByAuthor();
                break;

            default:
                return false;
        }
        return true;
    }


    private void showCatalog() {
        catalog.show();
    }

    private void addBook() {
        Book newBook = new Book();
        newBook.input();
        catalog.add(newBook);
    }

    private void addNewspaper() {
        Newspaper newNewspaper = new Newspaper();
        newNewspaper.input();
        catalog.add(newNewspaper);
    }

    private void addAlmanac() {
        Almanac newAlmanac = new Almanac();
        newAlmanac.input();
        catalog.add(newAlmanac);
    }

    private void addRandomCatalog() {
        catalog.addRandom();
    }

    private void deleteByTitle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input title: ");
        String title = scanner.nextLine();

        catalog.deleteByTitle(title);
    }

    private void searchByTitle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input title: ");
        String title = scanner.nextLine();

        Publication[] findPublication = catalog.searchByTitle(title);
        if (findPublication.length == 0) {
            System.out.println("Not found...");
            return;
        }

        System.out.println("Find publications: ");
        for (Publication publication : findPublication) {
            publication.print();
        }
    }

    private void searchByAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input author: ");
        String author = scanner.nextLine();

        Publication[] findPublication = catalog.searchByAuthor(author);
        if (findPublication.length == 0) {
            System.out.println("Not found...");
            return;
        }

        System.out.println("Find publications: ");
        for (Publication publication : findPublication) {
            publication.print();
        }
    }
}
