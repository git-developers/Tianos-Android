package xyz.tianos.software.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ApiCategory implements Serializable {

    @SerializedName("parent")
    private Category parent;

    @SerializedName("children")
    private List<ApiCategory> children;

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<ApiCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ApiCategory> children) {
        this.children = children;
    }
}