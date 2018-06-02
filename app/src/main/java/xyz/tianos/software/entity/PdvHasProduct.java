package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jafeth on 3/30/17.
 */

public class PdvHasProduct implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("point_of_sale_id")
    private int pointOfSaleId;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("id_backend")
    private int idBackend;

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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdBackend() {
        return idBackend;
    }

    public void setIdBackend(int idBackend) {
        this.idBackend = idBackend;
    }

    @Override
    public String toString() {
        return this.username +" ("+ this.uuid +")";
    }

}