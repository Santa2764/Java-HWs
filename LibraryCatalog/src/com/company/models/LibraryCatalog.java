package com.company.models;

import com.company.tests.TaskFactory;

import java.util.Arrays;

public class LibraryCatalog implements ICatalog {
    private Publication[] publications;

    public LibraryCatalog() {}

    @Override
    public void initialize() {
        publications = TaskFactory.getRandomPublications();
    }

    @Override
    public void add(Publication publication) {
        publications = Arrays.copyOf(publications, publications.length + 1);
        publications[publications.length - 1] = publication;
    }

    @Override
    public void addRandom() {
        Publication randPublication = TaskFactory.getRandomPublication();
        add(randPublication);
    }

    @Override
    public Publication deleteByTitle(String title) {
        for (int i = 0; i < publications.length; i++) {
            if (publications[i].getTitle().equals(title)) {
                Publication deletedPublication = publications[i];
                publications[i] = publications[publications.length - 1];
                publications = Arrays.copyOf(publications, publications.length - 1);
                return deletedPublication;
            }
        }
        return null;
    }

    @Override
    public void show() {
        for (Publication publication : publications) {
            publication.print();
        }
    }

    @Override
    public Publication[] searchByTitle(String title) {
        int count = 0;
        for (Publication publication : publications) {
            if (publication.getTitle().contains(title)) {
                count++;
            }
        }

        Publication[] findPublications = new Publication[count];
        int index = 0;

        for (Publication publication : publications) {
            if (publication.getTitle().contains(title)) {
                findPublications[index++] = publication;
            }
        }

        return findPublications;
    }

    @Override
    public Publication[] searchByAuthor(String author) {
        int count = 0;
        for (Publication publication : publications) {
            if (publication.hasAuthor(author)) {
                count++;
            }
        }

        Publication[] findPublications = new Publication[count];
        int index = 0;

        for (Publication publication : publications) {
            if (publication.hasAuthor(author)) {
                findPublications[index++] = publication;
            }
        }

        return findPublications;
    }
}
