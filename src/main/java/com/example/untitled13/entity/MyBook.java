package com.example.untitled13.entity;


import java.util.Objects;

public class MyBook {

    private int id;
    private double rating=0;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private String name;

    public MyBook(MyAuthors authors, String name_and_surname_author) {
        this.authors = authors;
        this.name_and_surname_author = name_and_surname_author;

    }

    public MyBook() {
    }

    private int number_grade=0;
    private double summa_grade=0;

    private String name_and_surname_author;

    public String getName_and_surname_author() {
        return name_and_surname_author;
    }

    public void setName_and_surname_author(String name_and_surname_author) {
        this.name_and_surname_author = name_and_surname_author;
    }

    public int getNumber_grade() {
        return number_grade;
    }

    public void setNumber_grade(int number_grade) {
        this.number_grade = number_grade;
    }

    public double getSumma_grade() {
        return summa_grade;
    }

    public void setSumma_grade(double summa_grade) {
        this.summa_grade = summa_grade;
    }


    private MyAuthors authors;

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

    private MyAuthors getAuthors() {
        return authors;
    }

    public void setAuthors(MyAuthors authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number_grade=" + number_grade +
                ", summa_grade=" + summa_grade +
                ", authors=" + authors +
                '}';
    }


}
