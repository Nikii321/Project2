package com.example.untitled13.controller;

import com.example.untitled13.entity.MyBook;
import com.example.untitled13.service.CommunicationWithRest;
import com.example.untitled13.entity.MyAuthors;
import com.example.untitled13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommunicationWithRest communication;


    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsersForAdmin());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @GetMapping("/admin/books")
    public String bookList(Model model) {
        model.addAttribute("allAdminBooks", communication.ShowAllBooks());
        return "booksadmin";
    }

    @PostMapping("/admin/books")
    public String deleteBook(@RequestParam(required = true, defaultValue = "") int bookId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            communication.deleteBookById(bookId);
        }


        return "redirect:/admin/books";
    }

    @GetMapping("/admin/author")
    public String AuthorList(Model model) {
        model.addAttribute("allAdminAuthor", communication.ShowAllAuthors());

        return "authors";
    }

    @PostMapping("/admin/author")
    public String deleteAuthor(@RequestParam(required = true, defaultValue = "") int AuthorID,
                               @RequestParam(required = true, defaultValue = "") String action,
                               Model model) {
        System.out.println("->" + AuthorID);
        System.out.println(action);

        communication.deleteAuthorById(AuthorID);

        return "redirect:/admin/author";
    }

    @GetMapping("/admin/author/save")
    private String SaveAuthor(Model model) {
        model.addAttribute("authorSave", new MyAuthors());

        return "auhtorsave";

    }

    @PostMapping("/admin/author/save")
    public String addAuthor(@ModelAttribute("authorSave") MyAuthors author, Model model) {
        if(communication.ShowAuthorByNameAndSurname(author.getName(), author.getSurname())!=null) {
            model.addAttribute("authorSaveError", "Author is already there");
            return "auhtorsave";
        }


        communication.saveAuthor(author);

        return "redirect:/admin/author";
    }
    @GetMapping("/admin/book/save/{id}")
    private String SaveBook(@PathVariable int id,Model model) {
        MyAuthors author = communication.ShowAuthorByID(id);
        model.addAttribute("bookSave", new MyBook(author,author.getName()+" "+author.getSurname()));

        return "booksave";

    }

    @PostMapping("/admin/book/save/{id}")
    public String addBook(@ModelAttribute("bookSave") MyBook book, @PathVariable int id,Model model) {
        if(communication.ShowBookByName(book.getName())!=null && communication.ShowBookByName(book.getName()).getName_and_surname_author().equals(book.getName_and_surname_author())) {
            model.addAttribute("bookSaveError", "Book is already there");
            return "booksave";
        }
        System.out.println(communication.ShowAuthorByID(id).getBooks().contains(book));


        communication.saveBook(book);

        return "redirect:/admin/author";
    }
}
