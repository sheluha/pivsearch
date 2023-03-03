package com.pivseacrh.pivseacrh.entity;

import lombok.Data;

import java.util.List;

@Data
public class Organisation {
    private String name;

    private String address;

    private List<Double> coordinates;

    private String hours;

    private String url;

    private List<String> categories;

    @Override
    public String toString() {
        return name + "\n"+
                address +  "\n"+
                hours  + "\n"+
                url  + "\n"+
                categories;
    }
}
