package org.example.touristguide.repository;

import org.example.touristguide.model.City;
import org.example.touristguide.model.Tag;
import org.springframework.stereotype.Repository;
import org.example.touristguide.model.TouristAttraction;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class TouristRepository {

    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Tivoli",
                "En historisk forlystelsespark i hjertet af København.",
                City.KØBENHAVN,
                Arrays.asList(Tag.FORLYSTELSESPARK, Tag.RESTAURANT)));

        attractions.add(new TouristAttraction("Den Lille Havfrue",
                "En berømt bronzestatue inspireret af H.C. Andersens eventyr.",
                City.KØBENHAVN,
                Arrays.asList(Tag.ARKITEKTUR, Tag.HISTORIE)));

        attractions.add(new TouristAttraction("Legoland Billund",
                "En familievenlig forlystelsespark bygget af LEGO-klodser.",
                City.BILLUND,
                Arrays.asList(Tag.FORLYSTELSESPARK)));

        attractions.add(new TouristAttraction("Nyhavn",
                "Et ikonisk havnekvarter med farverige bygninger og livlige caféer.",
                City.KØBENHAVN,
                Arrays.asList(Tag.RESTAURANT, Tag.ARKITEKTUR)));

        attractions.add(new TouristAttraction("Kronborg Slot",
                "Shakespeares berømte Hamlet-slot, fyldt med historie og kultur.",
                City.HELSINGØR,
                Arrays.asList(Tag.HISTORIE, Tag.ARKITEKTUR, Tag.MUSEUM)));

        attractions.add(new TouristAttraction("Møns Klint",
                "Storslåede hvide kridtklinter med fantastisk udsigt over havet.",
                City.MØN,
                Arrays.asList(Tag.NATUROPLEVELSE, Tag.LANDSKAB, Tag.UDSIGTSPUNKT)));

        attractions.add(new TouristAttraction("Rundetårn",
                "Et gammelt observatorium med en unik spiralrampe og flot udsigt.",
                City.KØBENHAVN,
                Arrays.asList(Tag.UDSIGTSPUNKT, Tag.HISTORIE, Tag.ARKITEKTUR)));

        attractions.add(new TouristAttraction("ARoS Aarhus Kunstmuseum",
                "Et moderne kunstmuseum kendt for sin regnbuepanorama.",
                City.AARHUS,
                Arrays.asList(Tag.KUNST, Tag.MUSEUM)));

        attractions.add(new TouristAttraction("Ribe Domkirke",
                "Danmarks ældste domkirke i en charmerende middelalderby.",
                City.RIBE,
                Arrays.asList(Tag.HISTORIE, Tag.ARKITEKTUR, Tag.UDSIGTSPUNKT)));

        attractions.add(new TouristAttraction("Nationalmuseet",
                "Danmarks største kulturhistoriske museum med unikke udstillinger.",
                City.KØBENHAVN,
                Arrays.asList(Tag.HISTORIE, Tag.MUSEUM)));
    }

    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    public TouristAttraction getAttractionByName(String name) {
        TouristAttraction current = null;
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                current = attraction;
            }
        }
        return current;
    }

    public TouristAttraction addAttraction(TouristAttraction newAttraction) {
        attractions.add(newAttraction);
        return newAttraction;
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (updatedAttraction.getName().equals(attractions.get(i).getName())) {
                attractions.set(i, updatedAttraction);
            }
        }
        if (!attractions.contains(updatedAttraction)) {
            attractions.add(updatedAttraction);
        }
    }
    public TouristAttraction deleteAttraction(String name) {

        Iterator<TouristAttraction> iterator = attractions.iterator();
        while (iterator.hasNext()) {
            TouristAttraction attraction = iterator.next();
            if (attraction.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return attraction;
            }
        }
        return null;
    }


}

