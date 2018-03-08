package com.example.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Semester implements Serializable  {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Image name (Without extension)
    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString()  {
        return this.name + " (Item: "+ this.id+")";
    }
}