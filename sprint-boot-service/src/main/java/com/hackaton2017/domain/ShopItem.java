package com.hackaton2017.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
public class ShopItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long code;

    private String name;

    private Long cost;

    private Collection<String> size;

    private Collection<String> colors;

    private String imageLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Collection<String> getSize() {
        return size;
    }

    public void setSize(Collection<String> size) {
        this.size = size;
    }

    public Collection<String> getColors() {
        return colors;
    }

    public void setColors(Collection<String> colors) {
        this.colors = colors;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", size=" + size +
                ", colors=" + colors +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}