package org.example.touristguide.service;

import org.springframework.stereotype.Service;
import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.repository.TouristRepository;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public TouristAttraction addAttraction (TouristAttraction attraction) {
        return touristRepository.addAttraction(attraction);
    }

    public void saveAttraction (TouristAttraction attraction) {
        touristRepository.saveAttraction(attraction);
    }

    public void updateAttraction (TouristAttraction attraction){
        touristRepository.updateAttraction(attraction);
    }

    public TouristAttraction deleteAttraction (String name) {
       return touristRepository.deleteAttraction(name);
    }


}
