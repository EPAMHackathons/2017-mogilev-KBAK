package com.hackaton2017.domain;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
public enum Shop {

    WILDBERRIES("Wildberries.by"),
    EBAY("Ebay.com");

    private String shopName;

    Shop(final String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}