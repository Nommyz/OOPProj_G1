package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestCost {
    @Test
    void Cost(){
        Territory territory = new Territory();

        Unit test1 = new Unit("test1",territory,5,5,1000);
        test1.printUnitData();
        test1.move(Direction.UP_RIGHT);

        test1.cost(999999); //not enough budget
        test1.printUnitData();
        test1.newTurn();

        test1.cost(-99); //invest cant below 0
        test1.printUnitData();
        test1.newTurn();

        test1.cost(99999999^99999999); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.cost((99999999^99999999)*(-99)); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.cost(0);
        test1.printUnitData();
        test1.newTurn();

        test1.cost(500);
        test1.printUnitData();
        test1.newTurn();


    }

}