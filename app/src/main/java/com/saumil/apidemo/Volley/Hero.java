package com.saumil.apidemo.Volley;

/**
 * Created by Saumil on 1/8/2018.
 */

public class Hero {
    String name,imageUrl;

    public Hero(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
