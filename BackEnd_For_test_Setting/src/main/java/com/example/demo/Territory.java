package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Territory {
    private Configuration configFile;
    private Region[][] territory;
    private int height;
    private int width;
    private double init_dep;
    private double max_dep;
    private double region_int_pct;
    private String[][] owner_name;
    private double [][] dep;

    public Territory(Configuration configFile){
        this.configFile = configFile;

        /*height = 10;
        width = 10;
        init_dep = 50000;
        max_dep = 1000;
        region_int_pct = 5;*/

        height = configFile.getRows();
        width = configFile.getCols();
        init_dep = configFile.getInit_dep();
        max_dep = configFile.getMax_dep();
        region_int_pct = configFile.getInterest_pct();

        territory = new Region[height][width];
        owner_name = new String[height][width];
        dep = new double[height][width];
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width ; j++){
                territory[i][j] = new Region(init_dep,max_dep,region_int_pct);
                owner_name[i][j] = territory[i][j].getOwnerName();
                dep[i][j] = territory[i][j].getDep();
            }
        }
    }
}
