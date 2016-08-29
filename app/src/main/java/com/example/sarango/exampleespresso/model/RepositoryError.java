package com.example.sarango.exampleespresso.model;

/**
 * Created by sarango on 29/08/2016.
 */
public class RepositoryError extends Exception {
    private int id;

    public RepositoryError(String detailMessage) {
        super(detailMessage);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
