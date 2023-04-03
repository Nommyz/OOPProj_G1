package com.example.demo;

import lombok.Getter;

@Getter
public class MessageChangeConfig {
    private int rows;
    private int cols;
    private int init_plan_time;
    private double init_budget;
    private double init_dep;
    private double init_center_dep;
    private int plan_rev_time;
    private double rev_cost;
    private double max_dep;
    private double interest_pct;
}
