package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestInvest {
    @Test
    void InvestRegion(){
        Territory territory = new Territory();
        
        Unit test1 = new Unit("test1",territory,4,4,1000);
        test1.printInfo();

        test1.invest(999999); //not enough budget
        test1.printInfo();
        test1.newTurn();
        
        test1.invest(-99); //invest cant below 0
        test1.printInfo();
        test1.newTurn();
        
        test1.invest(100/0);//error
        test1.printInfo();
        test1.newTurn();

        test1.invest(77/3); // 77/3=25.6667
        test1.printInfo();
        test1.newTurn();
        
        test1.invest(99999999^99999999); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.invest(0);
        test1.printInfo();
        test1.newTurn();

        test1.invest(500);
        test1.printInfo();
        test1.newTurn();

        
    }
    @Test
    void InvestOpponentRegion(){
        // Territory territory = new Territory();
        // Unit p1 = new Unit("P1",territory,5,5,1000);
        // Unit p2 = new Unit("P2",territory,10,5,1000);
        // System.out.println(p1.opponent());
        // p1.printInfo();
        // p1.move(Direction.DOWN_RIGHT);
        // p1.printInfo();
        // p1.invest(100);
        // p1.printInfo();
    }
    @Test
    void InvestNoOwnerRegion(){
        // Territory territory = new Territory();
        // Unit p1 = new Unit("P1",territory,5,5,1000);
        // p1.printInfo();
        // p1.move(Direction.DOWN_RIGHT);
        // p1.printInfo();
        // p1.invest(100);
        // p1.printInfo();
    }
}