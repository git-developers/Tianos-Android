package xyz.elcomercio.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jafeth on 3/30/17.
 */

public class Visit implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("visitStart")
    private String visitStart;

    @SerializedName("visitEnd")
    private String visitEnd;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("pointOfSale")
    private int pointOfSaleId;

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

    public String getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(String visitStart) {
        this.visitStart = visitStart;
    }

    public String getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(String visitEnd) {
        this.visitEnd = visitEnd;
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

    public String getPointOfSaleIdStr() {
        return String.valueOf(pointOfSaleId);
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
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