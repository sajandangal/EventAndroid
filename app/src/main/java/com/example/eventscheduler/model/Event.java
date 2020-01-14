package com.example.eventscheduler.model;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("image")
    private String image;
    @SerializedName("desc")
    private String desc;

    public Event(String name, String location, String image, String desc) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
