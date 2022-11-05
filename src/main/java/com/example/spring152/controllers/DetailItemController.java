package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.RequestModel;
import com.example.spring152.repos.ItemRepo;
import com.example.spring152.repos.RequestRepo;
import com.example.spring152.services.CurrencyService;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/detailItem")
public class DetailItemController {
    final ItemRepo itemRepo;
    final RequestRepo requestRepo;

    final CurrencyService currencyService;
    final FirebaseImageService firebaseImageService;

    public DetailItemController(ItemRepo itemRepo, RequestRepo requestRepo, CurrencyService currencyService, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.requestRepo = requestRepo;
        this.currencyService = currencyService;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable long id,
                         Model model) {
        ItemModel itemModel = itemRepo.findById(id);
        itemModel.setUrl(firebaseImageService.getImageUrl(itemModel.getUrl()));
        String cur = "BYN";
        model.addAttribute("item", itemModel);
        model.addAttribute("cur", cur);
        return "detail";
    }

    @PostMapping("/{id}")
    public String setPriceAtRate(@PathVariable long id,
                                 Model model, @RequestParam String currency) throws IOException {
        ItemModel itemModel = itemRepo.findById(id);
        String priceAtRate = String.format("%.2f", Double.parseDouble(itemModel.getPrice()) / currencyService.rate(currency));
        itemModel.setPrice(priceAtRate);
        itemModel.setUrl(firebaseImageService.getImageUrl(itemModel.getUrl()));
        model.addAttribute("item", itemModel);
        model.addAttribute("cur", currency);
        return "detail";
    }

    @PostMapping("/post/{id}")
    public RedirectView ok(@PathVariable long id,
                           @RequestParam String name,
                           @RequestParam String contact) {
        RequestModel requestModel = new RequestModel();
        requestModel.setItemId(id);
        requestModel.setName(name);
        requestModel.setContact(contact);
        requestRepo.save(requestModel);
        return new RedirectView("/allItems");
    }
}

