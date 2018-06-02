package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListPdvHasProduct implements Serializable {

    @SerializedName("pdvHasProduct")
    private List<PdvHasProduct> listPdvHasProduct;

    public List<PdvHasProduct> getListPdvHasProduct() {
        return listPdvHasProduct;
    }

    public void setListPdvHasProduct(List<PdvHasProduct> listPdvHasProduct) {
        this.listPdvHasProduct = listPdvHasProduct;
    }
}