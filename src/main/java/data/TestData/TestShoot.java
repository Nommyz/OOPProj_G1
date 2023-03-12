package data.TestData;


import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;

public class TestShoot {
    @Test
    void ShootCenterCity(){
        Territory territory = new Territory();
        Unit p1 = new Unit("P1",territory,5,5,1000);
        p1.move(Direction.UP);
        p1.shoot(Direction.DOWN, 100);
        p1.printUnitData();
        territory.region(p1.getCityCenterPosition()).printRegionData();
    }
}