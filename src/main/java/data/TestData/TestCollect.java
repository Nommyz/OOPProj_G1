package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;

public class TestCollect {
    @Test
    void collectRegion(){
        Territory territory = new Territory();

        Unit test1 = new Unit("test1",territory,4,4,1000);
        test1.move(Direction.DOWN);
        test1.invest(500);
        test1.printUnitData();
        test1.newTurn();
        test1.collect(10);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(-99); //invest cant below 0
        test1.printUnitData();
        test1.newTurn();

        test1.collect(50);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(99999999^99999999); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.collect((99999999^99999999)*(-99)); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.collect(0);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(400);
        test1.printUnitData();
        test1.newTurn();
        territory.printTerritoryData();
    }
    @Test
    void collectOpponentRegion(){
        Territory territory = new Territory();
        Unit test1 = new Unit("test1",territory,5,5,1000);
        Unit test2 = new Unit("test2",territory,6,5,1000);
        System.out.println(test1.opponent());

        test1.move(Direction.DOWN);
        test1.newTurn();
        test1.collect(10);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(-99); //invest cant below 0
        test1.printUnitData();
        test1.newTurn();

        test1.collect(50);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(99999999^99999999); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.collect((99999999^99999999)*(-99)); //out of range
        test1.printUnitData();
        test1.newTurn();

        test1.collect(0);
        test1.printUnitData();
        test1.newTurn();

        test1.collect(500);
        test1.printUnitData();
        test1.newTurn();
        territory.printTerritoryData();
    }

}