package com.example.untitled13.controller;

import com.example.untitled13.service.CommunicationWithRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {
    @Autowired
    private CommunicationWithRest communication;

    @GetMapping("/main")
    public String userList(Model model) {
        model.addAttribute("allAuthors", communication.ShowAllAuthors());
        System.out.println("---------------------"+"\n"+"\n"+"\n"+communication.ShowAllAuthors()+"\n"+"\n"+"\n"+"--------------------------");
        return "main";
    }
    @PostMapping("/main")
    public String showAuthorBook(@RequestParam(required = true, defaultValue = "" ) int id,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("showBook")){
            communication.ShowAuthorByID(id);
            System.out.println(communication.ShowAuthorByID(id).getBooks());
            model.addAttribute("allAuthorBook",communication.ShowAuthorByID(id).getBooks());
        }
        return "authorsbooks";
    }



}
