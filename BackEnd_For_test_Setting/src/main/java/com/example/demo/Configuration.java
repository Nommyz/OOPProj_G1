package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Configuration {
    private int rows;
    private int cols;
    private int init_plan_time;
    private double init_budget;
    private double init_dep;
    private double init_center_dep;
    private int rev_plan_time;
    private double rev_cost;
    private double max_dep;
    private double interest_pct;

    public Configuration(){
        rows = 11;
        cols = 11;
        init_plan_time = 300;
        init_budget = 10000;
        init_dep = 700;
        init_center_dep = 1000;
        rev_plan_time = 1800;
        rev_cost = 300;
        max_dep = 5000;
        interest_pct = 5;
    }

    public Configuration changeConfig(MessageChangeConfig messageChangeConfig){
        rows = messageChangeConfig.getRows();
        cols = messageChangeConfig.getCols();
        init_plan_time = messageChangeConfig.getInit_plan_time();
        init_budget = messageChangeConfig.getInit_budget();
        init_dep = messageChangeConfig.getInit_dep();
        init_center_dep = messageChangeConfig.getInit_center_dep();
        rev_plan_time = messageChangeConfig.getPlan_rev_time();
        rev_cost = messageChangeConfig.getRev_cost();
        max_dep = messageChangeConfig.getMax_dep();
        interest_pct = messageChangeConfig.getInterest_pct();
        System.out.println(rows);
        return this;
    }

    public Configuration setDefault(){
        rows = 10;
        cols = 10;
        init_plan_time = 300;
        init_budget = 10000;
        init_dep = 500;
        init_center_dep = 1000;
        rev_plan_time = 1800;
        rev_cost = 300;
        max_dep = 5000;
        interest_pct = 5;
        return this;
    }
}
