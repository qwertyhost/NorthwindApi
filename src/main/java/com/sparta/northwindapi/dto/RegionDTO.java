package com.sparta.northwindapi.dto;

public class RegionDTO {

    private int id;
    private String regionDescription;

    public RegionDTO(int id, String regionDescription) {
        this.id = id;
        this.regionDescription = regionDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}
