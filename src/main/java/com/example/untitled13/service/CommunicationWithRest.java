package com.example.untitled13.service;

import com.example.untitled13.entity.MyAuthors;
import com.example.untitled13.entity.MyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CommunicationWithRest {
    @Autowired
    private RestTemplate restTemplate;
    private final String Url ="http://localhost:8090/api";

    public List<MyAuthors> ShowAllAuthors(){
        ResponseEntity<List<MyAuthors>> responseEntity = restTemplate.exchange(Url + "/authors", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<MyAuthors>>() {});
        List<MyAuthors> authors = responseEntity.getBody();
        return authors;
    }
    public MyAuthors ShowAuthorByID(int id){
        MyAuthors myAuthors = restTemplate.getForObject(Url+"/authors/"+id,MyAuthors.class);
        return myAuthors;
    }
    public MyAuthors ShowAuthorByNameAndSurname(String name, String surname){
        System.out.println("/authors/name/{name}/{surname}");
        System.out.println(name);
        System.out.println(surname);
        MyAuthors myAuthors = restTemplate.getForObject(Url+"/authors/name/"+name+"/"+surname,MyAuthors.class);
        return myAuthors;
    }
    public void saveAuthor(MyAuthors author){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(Url+"/authors", author,String.class);
    }
    public void updateAuthor(MyAuthors author){
        restTemplate.put(Url+"/authors",author);
    }
    public void deleteAuthorById(int id){
        System.out.println(id+"<- me");
        restTemplate.delete(Url+"/authors/"+id);
    }
    public List<MyBook> ShowAllBooks(){
        ResponseEntity<List<MyBook>> responseEntity = restTemplate.exchange(Url + "/books", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<MyBook>>() {});
        List<MyBook> books = responseEntity.getBody();
        return books;

    }
    public MyBook ShowBookByID(int id){
        MyBook book = restTemplate.getForObject(Url+"/books/"+id,MyBook.class);
        return book;
    }
    public MyBook ShowBookByName(String name){
        MyBook book = restTemplate.getForObject(Url+"/books/name/"+name,MyBook.class);

        return book;
    }
    public void saveBook(MyBook book){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(Url+"/books", book,String.class);

    }
    public  void updateBook(MyBook book){
        restTemplate.put(Url+"/books",book);

    }
    public void deleteBookById(int id){
        restTemplate.delete(Url+"/books/"+id);


    }
    public void PassGrade(int id, int grade){
        ResponseEntity<String> responseEntity = restTemplate.exchange(Url+"/books/rating/"+id+"/"+grade, HttpMethod.GET,
                null, new ParameterizedTypeReference<String>() {});


    }
}
