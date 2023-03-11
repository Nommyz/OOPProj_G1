package data.TestData;

import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestPay {
    @Test
    void Pay(){
        Territory territory = new Territory();
        
        Unit test1 = new Unit("test1",territory,4,4,1000);
        test1.printInfo();
        test1.move(Direction.UP_RIGHT);

        test1.pay(999999); //not enough budget
        test1.printInfo();
        test1.newTurn();
        
        test1.pay(-99); //invest cant below 0
        test1.printInfo();
        test1.newTurn();
        
        test1.pay(100/0);//error
        test1.printInfo();
        test1.newTurn();

        test1.pay(77/3); // 77/3=25.6667
        test1.printInfo();
        test1.newTurn();
        
        test1.pay(99999999^99999999); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.pay((99999999^99999999)*(-99)); //out of range
        test1.printInfo();
        test1.newTurn();

        test1.pay(0);
        test1.printInfo();
        test1.newTurn();

        test1.pay(500);
        test1.printInfo();
        test1.newTurn();

        
    }
    
}