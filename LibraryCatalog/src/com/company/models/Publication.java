package com.company.models;

public abstract class Publication {
    protected String title;

    public Publication() {
        this("");
    }

    public Publication(String title) {
        this.title = title;
    }

    public abstract void print();

    public abstract void input();

    public abstract boolean hasAuthor(String author);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
