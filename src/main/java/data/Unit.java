package data;

import evaluator.Direction;

public class Unit {
    public String name;
    public int[] position;
    public Territory map;
    public Unit(String name,Territory map){
        position = new int[2];
        position[0] = 0;
        position[1] = 0;
        this.name = name;
        this.map = map;
    }

    public void move(Direction direction){

    }
    public void shoot(Direction direction){

    }
    public void invest(){

    }
    public void getPosition(){
        System.out.println("row: " + position[0] + " column: " + position[1]);
    }
}
