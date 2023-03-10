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
        Unit p1 = new Unit("P1",territory,5,5,1000);
        p1.printInfo();
        p1.move(Direction.UP_RIGHT);
        p1.invest(200);
        p1.printInfo();
        p1.newTurn();
        p1.collect(100);
        p1.printInfo();
    }
    @Test
    void collectOpponentRegion(){
        Territory territory = new Territory();
        Unit p1 = new Unit("P1",territory,5,5,1000);
        Unit p2 = new Unit("P2",territory,6,6,1000);
        System.out.println(p1.opponent());
        p1.printInfo();
        p1.move(Direction.DOWN_RIGHT);
        p1.printInfo();
        p1.collect(100);
        p1.printInfo();
    }
    @Test
    void collectNoOwnerRegion(){
        Territory territory = new Territory();
        Unit p1 = new Unit("P1",territory,5,5,1000);
        p1.printInfo();
        p1.move(Direction.DOWN_RIGHT);
        p1.printInfo();
        p1.collect(100);
        p1.printInfo();
    }
}