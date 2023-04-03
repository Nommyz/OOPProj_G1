package com.example.demo;

import lombok.Getter;

@Getter
public class Player {
    private String name;
    private int order;
    private double budget;
    private int init_plan_time;
    private int rev_plan_time;
    public Player(String name, int order){
        this.name = name;
        this.order = order;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setInit_plan_time(int init_plan_time) {
        this.init_plan_time = init_plan_time;
    }

    public void setPlan_rev_time(int rev_plan_time) {
        this.rev_plan_time = rev_plan_time;
    }
}
