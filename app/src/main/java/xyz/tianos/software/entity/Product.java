package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jafeth on 3/30/17.
 */

public class Product implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("username")
    private String username;

    @SerializedName("image")
    private String image;

//    private Attendance attendance;

    public Product() {

    }

    public Product(String username, String slug) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return "ic_view_quilt_black_24dp";
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
        return this.username +" ("+ this.name +")";
    }

}