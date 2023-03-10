package data.TestData;


import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;

public class TestMove {
    Territory territory = new Territory();
    Unit player = new Unit("Player1",territory);
    Unit player2 = new Unit("Player2",territory);

    public void delay(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void MoveUpTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UP);
        }
        player.move(Direction.DOWN);
    }

    @Test
    void MoveDownTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWN);
        }
        player.move(Direction.UP);
    }

    @Test
    void MoveUprightTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UP_RIGHT);
        }
    }

    @Test
    void MoveUpLeftTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UP_LEFT);
        }
    }

    @Test
    void MoveDownLeftTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWN_LEFT);
        }
    }
    @Test
    void MoveDownRightTest(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWN_RIGHT);
        }
    }

    @Test
    void RandomMove(){
        player.printInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.randomMove();
        }
    }
    @Test
    void NotEnoughBudgetToMove(){
        Unit player = new Unit("p",territory,8,8,0);
        player.printInfo();
        player.move(Direction.UP);
    }

}