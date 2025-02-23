package org.example.touristguide.controller;

import org.example.touristguide.model.City;
import org.example.touristguide.model.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAttractions()); //Fetch all attractions
        return "attraction-list";
    }

    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "attraction-details";
    }

    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tag.values());
        return "attraction-form";
    }

    @PostMapping("/save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        if (touristAttraction == null) {
            throw new IllegalArgumentException("Ugyldig attraktion");
        }
        model.addAttribute("editAttraction", touristAttraction);
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tag.values());
        return "attraction-form";
    }

    @PostMapping("/update")
    public String updateAttraction(Model model, @ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        model.addAttribute("updatedAttraction", touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name, Model model) {
        TouristAttraction deletedAttraction = touristService.deleteAttraction(name);
        if (deletedAttraction == null) {
            throw new IllegalArgumentException("Ugyldig attraktion");
        }
        model.addAttribute("deletedAttraction", deletedAttraction);
        return "redirect:/attractions";
    }
}

