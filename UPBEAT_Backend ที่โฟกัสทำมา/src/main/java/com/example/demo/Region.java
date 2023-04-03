package com.example.demo;

import lombok.Getter;

@Getter
public class Region {
    private String ownerName;
    private double dep;
    private double max_dep;
    private double region_int_pct;

    public Region(double dep, double max_dep, double region_int_pct){
        this.dep = dep;
        this.max_dep = max_dep;
        this.region_int_pct = region_int_pct;
        ownerName = "";
    }
}
