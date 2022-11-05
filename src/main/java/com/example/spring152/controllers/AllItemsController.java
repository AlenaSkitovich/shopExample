package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.enums.ItemEnum;
import com.example.spring152.repos.ItemRepo;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {

    final ItemRepo itemRepo;
    final FirebaseImageService firebaseImageService;

    public AllItemsController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping
    public String getPage(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        list.get(0).getUrl();
        return "allItems";
    }

    @PostMapping
    public String setFilter(@RequestParam String max,
                            @RequestParam String min,
                            @RequestParam String itemName,
                            Model model) {
        List<ItemModel> list = itemRepo.findAll();
        if (max.equals("")) {
            max = "1000000";
        }
        if (min.equals("")) {
            min = "0";
        }
        String finalMin = min;
        String finalMax = max;
        list = list.stream()
                .filter(i -> i.getName().toLowerCase(Locale.ROOT).contains(itemName.toLowerCase()))
                .filter(i -> Integer.parseInt(i.getPrice()) >= Integer.parseInt(finalMin) && Integer.parseInt(i.getPrice()) <= Integer.parseInt(finalMax))
                .collect(Collectors.toList());


        model.addAttribute("items", list);
        return "allItems";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.AUTOS)).collect(Collectors.toList());
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }

    @GetMapping("/cats")
    public String getCats(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.CATS)).collect(Collectors.toList());
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }

    @GetMapping("/hometechnics")
    public String getHome(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.HOMETECHNICS)).collect(Collectors.toList());
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }

    @GetMapping("/drugs")
    public String getDrugs(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.DRUGS)).collect(Collectors.toList());
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }

    @GetMapping("/other")
    public String getOther(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.OTHER)).collect(Collectors.toList());
        list.stream().forEach(i->i.setUrl(firebaseImageService.getImageUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
}




