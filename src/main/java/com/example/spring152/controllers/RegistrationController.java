package com.example.spring152.controllers;

import com.example.spring152.models.RoleModel;
import com.example.spring152.models.UserModel;
import com.example.spring152.repos.RoleRepo;
import com.example.spring152.repos.UserRepo;
import com.example.spring152.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    final UserRepo userRepo;
    final RoleRepo roleRepo;

    public RegistrationController(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping
    /*public String addUser(UserModel userModel,
                          BindingResult bindingResult,
                          Model model,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm) {
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setPasswordConfirm(passwordConfirm);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userModel.getPassword().equals(userModel.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userModel)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}*/
    public RedirectView addUser(UserModel userModel,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String passwordConfirm) {
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setPasswordConfirm(passwordConfirm);
        RoleModel roleModel = new RoleModel(1L,"ROLE_USER");
        /*userModel.setRoles(Collections.singleton(roleModel));*/
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userRepo.save(userModel);
        RedirectView redirectView = new RedirectView("/");
        return redirectView;
    }
}