package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jafeth on 3/30/17.
 */

public class PointOfSale implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("isActive")
    private boolean active;

    @SerializedName("username")
    private String username;

    @SerializedName("image")
    private String image;

//    private Attendance attendance;

    public PointOfSale() {

    }

    public PointOfSale(String username, String slug) {
        this.username = username;
        this.slug = slug;
        this.active= true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLatitudeStr() {
        return Double.toString(latitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLongitudeStr() {
        return Double.toString(longitude);
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return "ic_home_black_24dp";
    }

    //    public String getName() {
//        try{
//            return name.trim();
//        }catch (NullPointerException e){
//            return "";
//        }
//
//    }

    @Override
    public String toString() {
        return this.username +" ("+ this.slug +")";
    }

}