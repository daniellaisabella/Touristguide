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

    @GetMapping("/attraction-list")
    public String getAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAttractions()); //Fetch all attractions
        return "attraction-list"; //Display attraction list
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionsByName(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        if (touristAttraction==null){
            throw new IllegalArgumentException("Ugyldig attraktion");
        }
        model.addAttribute("addAttraction",touristAttraction);
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tag.values());
        return "attraction-form";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attraction-list";
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteAttraction(@PathVariable String name) {
        TouristAttraction deleteAttraction = touristService.deleteAttraction(name);
        return new ResponseEntity<>(deleteAttraction, HttpStatus.OK);
    }


}
