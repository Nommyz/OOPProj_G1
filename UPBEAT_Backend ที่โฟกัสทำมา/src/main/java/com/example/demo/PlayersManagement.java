package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PlayersManagement {
    private Player[] players;
    private int maxPlayers = 4;
    private int numofPlayers;
    /*====================[ Variables for enterName (13-16) ]====================*/
    private String[] playersName;
    private boolean isExist;
    private boolean isFull;
    private String full;

    /*====================[ Variables about Game () ]====================*/
    private Configuration configFile;
    private double budget;
    private double rev_cost;
    private int init_plan_time;
    private int rev_plan_time;

    /*====================[ Constructor ]====================*/
    public PlayersManagement(Configuration configFile) {
        numofPlayers = 0;
        players = new Player[numofPlayers];

        playersName = new String[numofPlayers];
        isExist = false;
        isFull = false;
        full = "false";

        this.configFile = configFile;
    }

    public PlayersManagement enterName(MessageEnterName messageEnterName){
        if(!isFull){
            String name = messageEnterName.getName();
            isExist = false;

            for(int i=0; i<numofPlayers; i++){
                if(players[i].getName().equals(name)){
                    isExist = true;
                }
            }

            if(!isExist && numofPlayers < maxPlayers){
                numofPlayers++;
                Player temp[] = new Player[numofPlayers];
                String tempName[] = new String[numofPlayers];
                for(int i=0; i<numofPlayers-1; i++){
                    temp[i] = players[i];
                    tempName[i] = players[i].getName();
                }
                players = temp;
                playersName = tempName;
                players[numofPlayers - 1] = new Player(name, numofPlayers - 1);
                playersName[numofPlayers - 1] = name;
            }

            for(int i=0; i<numofPlayers; i++){
                System.out.println(i + " : " + players[i].getName());
            }
            System.out.println("Number of Player(s) : " + numofPlayers);

            if(numofPlayers == maxPlayers){
                isFull = true;
                full = "true";
            }
        }
        return this;
    }

    public PlayersManagement setFromConfig(){
        budget = configFile.getInit_budget();
        rev_cost = configFile.getRev_cost();
        init_plan_time = configFile.getInit_plan_time();
        rev_plan_time = configFile.getRev_plan_time();
        for(int i = 0 ; i < numofPlayers ; i++){
            players[i].setBudget(budget);
            players[i].setInit_plan_time(init_plan_time);
            players[i].setPlan_rev_time(rev_plan_time);
        }
        System.out.println("Budget : " + budget);
        return this;
    }
}
