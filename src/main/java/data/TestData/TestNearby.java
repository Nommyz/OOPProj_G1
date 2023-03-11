package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestNearby {
    Territory territory = new Territory();
        
    Unit test1 = new Unit("test1",territory,4,4,1000);
    Unit test2 = new Unit("test1",territory,3,3,1000);

    public void delay(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void nearbyUpTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.UP);
            test1.move(Direction.UP);
        }    
    }
    @Test
    void nearbyDownTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.DOWN);
            test1.move(Direction.DOWN);
        }    
    }
    @Test
    void nearbyUpLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.UP_LEFT);
            test1.move(Direction.UP_LEFT);
        }    
    }
    @Test
    void nearbyUpRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.UP_RIGHT);
            test1.move(Direction.UP_RIGHT);
        }    
    }
    @Test
    void nearbyDownLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.DOWN_LEFT);
            test1.move(Direction.DOWN_LEFT);
        }    
    }
    @Test
    void nearbyDownRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.nearby(Direction.DOWN_RIGHT);
            test1.move(Direction.DOWN_RIGHT);
        }    
    }

}

