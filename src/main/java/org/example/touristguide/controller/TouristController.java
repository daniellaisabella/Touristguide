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
        return "attraction-list"; //Display attraction list
    }

    @GetMapping("/tags/{name}")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionsByName(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    @GetMapping("/edit/{name}")
    public String editAttraction(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        if (touristAttraction==null){
            throw new IllegalArgumentException("Ugyldig attraktion");
        }
        model.addAttribute("editAttraction",touristAttraction);
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tag.values());
        return "attraction-form";
    }

    @PostMapping("/edit/{name}/update")
    public String updateAttraction(Model model, @ModelAttribute TouristAttraction touristAttraction){
        touristService.updateAttraction(touristAttraction);
        model.addAttribute("updatedAttraction",touristAttraction);
        return "updateSuccessful";
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteAttraction(@PathVariable String name) {
        TouristAttraction deleteAttraction = touristService.deleteAttraction(name);
        return new ResponseEntity<>(deleteAttraction, HttpStatus.OK);
    }

}
