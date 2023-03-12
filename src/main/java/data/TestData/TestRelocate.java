package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;

public class TestRelocate {

    @Test
    void Relocate(){
        Territory territory = new Territory();
        Unit p1 = new Unit("P1",territory,5,5,1000);
        p1.printUnitData();
        p1.move(Direction.DOWN);
        p1.move(Direction.DOWN);
        p1.relocate();
        territory.printTerritoryData();
        p1.printUnitData();

    }

    @Test
    void RelocateToOpponentRegion(){
        Territory territory = new Territory();
        Unit p1 = new Unit("P1",territory,5,5,1000);
        p1.printUnitData();
        p1.move(Direction.UP);
        p1.move(Direction.UP);
        p1.move(Direction.UP);
        p1.move(Direction.UP);
        Unit p2 = new Unit("P2",territory,1,5,1000);
        p1.relocate();
        territory.printTerritoryData();
        p1.printUnitData();
        p2.printUnitData();
    }
}