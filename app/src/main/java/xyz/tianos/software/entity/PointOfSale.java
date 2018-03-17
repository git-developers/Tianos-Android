package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jafeth on 3/30/17.
 */

public class PointOfSale implements Serializable {

//    public static final String INSERT_TYPE_LOGIN = "insert_type_login";
//    public static final String INSERT_TYPE_SELECTED_STUDENT = "insert_type_selected_student";
//    public static final String INSERT_TYPE_CHILDREN = "insert_type_children";

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
        return "ic_content_paste"; //image;
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