package com.pivseacrh.pivseacrh.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Root{
    private ArrayList<Feature> features;

    @Data
    public static class Feature{
        private String type;
        private Geometry geometry;
        private Properties properties;

    }
    @Data
    public static class Properties{
        private String name;
        private String description;
        @JsonProperty("CompanyMetaData")
        private CompanyMetaData companyMetaData;

    }
    @Data
    public static class CompanyMetaData{
        private String id;
        private String name;
        private String address;
        private String url;
        @JsonProperty("Phones")
        private ArrayList<Phone> phones;
        @JsonProperty("Categories")
        private ArrayList<Category> categories;
        @JsonProperty("Hours")
        private Hours hours;
    }

    @Data
    public static class Category{
        private String name;
    }

    @Data
    public static class Geometry{
        private String type;
        private ArrayList<Double> coordinates;
    }

    @Data
    public static class Hours{
        private String text;
    }

    @Data
    public static class Interval{
        private String from;
        @JsonProperty("to")
        private String myto;
    }

    @Data
    public static class Phone{
        private String type;
        private String formatted;
    }
}







