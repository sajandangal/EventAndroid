package com.example.eventscheduler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("notes")
    @Expose
    private List<Object> notes = null;
    @SerializedName("author")
    @Expose
    private Author author;




    public Event(Boolean done, String id, String name, String desc, String location, String image, List<Object> notes, Author author) {
        super();
        this.done = done;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.location = location;
        this.image = image;
        this.notes = notes;
        this.author = author;

    }

    public Event(String name, String desc, String location, String image, Author author) {
        this.name = name;
        this.desc = desc;
        this.location = location;
        this.image = image;
        this.author = author;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
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

    public List<Object> getNotes() {
        return notes;
    }

    public void setNotes(List<Object> notes) {
        this.notes = notes;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }



}