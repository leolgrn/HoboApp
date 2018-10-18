package com.lesson.hobo.data.model;

public class Hobo {

    private String lastName;
    private String firstName;
    private Double latitude;
    private Double longitude;
    private String genre;
    private String description;
    private boolean self;

    public Hobo(String lastName, String firstName, Double latitude, Double longitude, String genre, String description, boolean self) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.genre = genre;
        this.description = description;
        this.self = self;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }
}
