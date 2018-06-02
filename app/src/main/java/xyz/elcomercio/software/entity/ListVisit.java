package xyz.elcomercio.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListVisit implements Serializable {

    @SerializedName("visits")
    private List<Visit> listVisit;

    public List<Visit> getListVisit() {
        return listVisit;
    }

    public void setListVisit(List<Visit> listVisit) {
        this.listVisit = listVisit;
    }
}