package com.example.radio;

import java.io.Serializable;

public class Radio implements Serializable {
    String name, url;
    int logo;

    public Radio(String name, int logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
    }
}
