package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestCollect {
    @Test
    void collectRegion(){
        Territory territory = new Territory();
        
        Unit test1 = new Unit("test1",territory,4,4,1000);
        test1.printInfo();
        test1.move(Direction.UP_RIGHT);

        test1.collect(999999); //not enough budget
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(-99); //invest cant below 0
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(100/0);//error
        test1.printInfo();
        test1.newTurn();

        test1.collect(77/3); // 77/3=25.6667
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(99999999^99999999); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect((99999999^99999999)*(-99)); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect(0);
        test1.printInfo();
        test1.newTurn();

        test1.collect(500);
        test1.printInfo();
        test1.newTurn();
        
    }
    @Test
    void collectOpponentRegion(){
        Territory territory = new Territory();
        Unit test1 = new Unit("test1",territory,5,5,1000);
        Unit test2 = new Unit("test2",territory,10,5,1000);
        System.out.println(test1.opponent());

        test1.printInfo();
        test1.move(Direction.DOWN_RIGHT);
        test1.printInfo();

        test1.collect(999999); //not enough budget
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(-99); //invest cant below 0
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(100/0);//error
        test1.printInfo();
        test1.newTurn();

        test1.collect(77/3); // 77/3=25.6667
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(99999999^99999999); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect((99999999^99999999)*(-99)); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect(0);
        test1.printInfo();
        test1.newTurn();

        test1.collect(500);
        test1.printInfo();
        test1.newTurn();
    }
    @Test
    void collectNoOwnerRegion(){
        Territory territory = new Territory();
        Unit test1 = new Unit("test1",territory,5,5,1000);
        test1.printInfo();
        test1.move(Direction.DOWN_RIGHT);
        test1.printInfo();

        test1.collect(999999); //not enough budget
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(-99); //invest cant below 0
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(100/0);//error
        test1.printInfo();
        test1.newTurn();

        test1.collect(77/3); // 77/3=25.6667
        test1.printInfo();
        test1.newTurn();
        
        test1.collect(99999999^99999999); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect((99999999^99999999)*(-99)); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.collect(0);
        test1.printInfo();
        test1.newTurn();

        test1.collect(500);
        test1.printInfo();
        test1.newTurn();
    }
}