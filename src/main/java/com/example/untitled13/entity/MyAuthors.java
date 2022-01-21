package com.example.untitled13.entity;


import java.util.HashSet;
import java.util.Set;


public class MyAuthors {

    private int id;
    private String name;
    private String surname;

    private double rating=0;


    private Set<MyBook> books= new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<MyBook> getBooks() {
        return books;
    }

    public void setBooks(Set<MyBook> books) {
        this.books = books;
    }
    public  void addBook(MyBook book){
        books.add(book);
    }

    @Override
    public String toString() {
        return "MyAuthors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                ", books=" + books +
                '}';
    }
}
