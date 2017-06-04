package com.allsouls.dobrowins.airbitstest.models;

/**
 * Created on 02.06.2017.
 *
 * Class to let GSON to deserialize json string into arrayList
 *
 */

public class Product {
    private String name;
    private String type;

    public Product(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Product() {}

    @Override
    public String toString() {
        return name;
    }

    public String getType() {
        return type;
    }
}
