package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.enums.ItemEnum;
import com.example.spring152.repos.ItemRepo;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/item")
public class ItemController {
    final ItemRepo itemRepo;
    final FirebaseImageService firebaseImageService;

    public ItemController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteItem(@PathVariable long id) {
        itemRepo.deleteById(id);
        return new RedirectView("/admin/edit");
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable long id, Model model) {
        ItemModel itemModel = itemRepo.findById(id);
        itemModel.setUrl(firebaseImageService.getImageUrl(itemModel.getUrl()));
        model.addAttribute("item", itemModel);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public RedirectView setData(@PathVariable long id,
                                @RequestParam String name,
                                @RequestParam String price,
                                @RequestParam String disc,
                                @RequestParam String type) {
        ItemModel itemModel = itemRepo.findById(id);

        itemModel.setName(name);
        itemModel.setPrice(price);
        itemModel.setDisc(disc);

        RedirectView redirectView = new RedirectView("/");

        switch (type) {
            case "Не изменять":
                break;
            case "AUTOS":
                itemModel.setItemEnum(ItemEnum.AUTOS);
                break;
            case "CATS":
                itemModel.setItemEnum(ItemEnum.CATS);
                break;
            case "HOMETECHNICS":
                itemModel.setItemEnum(ItemEnum.HOMETECHNICS);
                break;
            case "DRUGS":
                itemModel.setItemEnum(ItemEnum.DRUGS);
                break;
            default:
                itemModel.setItemEnum(ItemEnum.OTHER);
                break;
        }
        itemRepo.save(itemModel);
        return redirectView;
    }
}
