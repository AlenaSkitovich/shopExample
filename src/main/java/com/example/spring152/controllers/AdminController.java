package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.repos.ItemRepo;
import com.example.spring152.services.FirebaseImageService;
import com.example.spring152.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final ItemRepo itemRepo;
    final FirebaseImageService firebaseImageService;

    @Autowired
    private UserService userService;

    public AdminController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/edit")
    public String editItems(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list.forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("list", list);
        return "editItem";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "users";
    }

    @PostMapping("/users")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "users";
    }
}
