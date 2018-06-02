package xyz.elcomercio.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Breadcrumb implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    private String uuid;

    private int pointOfSaleId;

    private int categoryId;

    private PointOfSale pointOfSale;

    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.username +" ("+ this.id +")";
    }

}