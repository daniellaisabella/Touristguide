package org.example.touristguide.model;

import java.util.List;

public class TouristAttraction {

    private String name;
    private String description;
    private City city;
    private List<Tag> tagList;

    public TouristAttraction(String name, String description, City city, List<Tag> tagList) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tagList = tagList;
    }

    public TouristAttraction() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
