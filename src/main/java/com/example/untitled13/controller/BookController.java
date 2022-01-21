package com.example.untitled13.controller;

import com.example.untitled13.service.CommunicationWithRest;
import com.example.untitled13.entity.MyGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Autowired
    private CommunicationWithRest communication;
    


    @GetMapping("/books")
    private String showAllBook(Model model){
        model.addAttribute("allBooks", communication.ShowAllBooks());
        System.out.println("---------------------"+"\n"+"\n"+"\n"+communication.ShowAllAuthors()+"\n"+"\n"+"\n"+"--------------------------");
        return "books";
    }
    @GetMapping("/grade/{id}")
    private String Grade(@PathVariable int id,Model model){

        model.addAttribute("GradePerson", new MyGrade());

        return "grade";
    }



    @PostMapping("/grade/{id}")
    private String PassGrade(@ModelAttribute("GradePerson") MyGrade grade,@PathVariable int id, Model model){
        if (grade.getGrade()<0){
            model.addAttribute("GradeNegativeError", "Grade must be more 0");
            return "grade";
        }
        if (grade.getGrade()>5){
            model.addAttribute("GradeToMuchError", "Grade must be little 5");
            return "grade";
        }
        communication.PassGrade(id,grade.getGrade());
        System.out.println(grade.getGrade());

        return "redirect:/books";
    }



}
