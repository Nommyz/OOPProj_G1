package data;

import evaluator.Direction;

public class Unit {
    public String name;
    public int[] position;
    public Territory map;
    

    public Unit(String name, Territory map) {
        position = new int[2];
        position[0] = 0;
        position[1] = 0;
        this.name = name;
        this.map = map;
    }

    public void move(Direction direction) {
        
    }

    public void shoot(Direction direction) {

    }

    public void invest(int TerritoryDeposit , int playerBudget , int investAmount) { // still confuse about "player may invest in a region belonging to no player as long as that region is adjacent to another region belonging to the player."
        
        
        if(playerBudget > 1){   // 1 can be change to balancing the game
            playerBudget--;
            playerBudget = playerBudget-investAmount;
            TerritoryDeposit = TerritoryDeposit+investAmount;
            System.out.println("You invest : " + investAmount + " Territory Deposit : " + TerritoryDeposit);
            if(TerritoryDeposit > 1000000){
                System.out.println("Reach Maximum Territory Deposit");
                int OverDeposit = TerritoryDeposit-1000000;
                TerritoryDeposit = TerritoryDeposit - OverDeposit;
                playerBudget = playerBudget+OverDeposit;
            }
        }else{
            playerBudget--;
            System.out.println("Don't have enough money"); //pls add something to end player's turn
        }
    }

    public void collect(int TerritoryDeposit , int playerBudget , int collectAmount) { 
        if(playerBudget > 1){ // 1 can be change to balancing the game
            playerBudget--;
            if(TerritoryDeposit < collectAmount){
                System.out.println("This Territory don't have money enough to collect"); 
            }else if(TerritoryDeposit >= collectAmount){
                playerBudget = playerBudget+collectAmount;
                TerritoryDeposit = TerritoryDeposit-collectAmount;
                System.out.println("You collected " + collectAmount + " TerritoryDeposit : " + TerritoryDeposit);
                if(TerritoryDeposit <= 0){
                    System.out.println("TerritoryDeposit is 0");
                    System.out.println("you lose this territory"); // pls add something that make player lose Territory
                }
            }
        }else{
            System.out.println("Don't have enough money"); //pls add something to end player's turn
        }
        
    }

    public void done() {

    }

    public void relocate() {

    }

    public long nearBy(Direction direction) {
        return 0;
    }

    public long getOpponent() {
        return 0;
    }

    public void getPosition() {
        System.out.println("row: " + position[0] + " column: " + position[1]);
    }
}
