package data.TestData;


import data.Territory;
import data.Unit;
import evaluator.Direction;
import org.testng.annotations.Test;

public class TestShoot {
    Territory territory = new Territory();
    Unit test1 = new Unit("test1",territory,3,3,50000);
    Unit player2 = new Unit("Player2",territory);

    public void delay(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    void ShootUpTest(){     // test amount and make the player to move up ว่ามันยิงออกนอกแผนที่ได้มั้ย
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.UP,999999);
            test1.printInfo();
            test1.shoot(Direction.UP,-99);
            test1.printInfo();
            test1.shoot(Direction.UP,100/0);
            test1.printInfo();
            test1.shoot(Direction.UP,4/3);
            test1.printInfo();
            test1.shoot(Direction.UP,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.UP,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.UP,0);
            test1.printInfo();
            test1.shoot(Direction.UP,500);
            test1.printInfo();
            test1.move(Direction.UP);
        }
       
    }

    @Test
    void ShootDownTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.DOWN,999999);
            test1.printInfo();
            test1.shoot(Direction.DOWN,-99);
            test1.printInfo();
            test1.shoot(Direction.DOWN,100/0);
            test1.printInfo();
            test1.shoot(Direction.DOWN,4/3);
            test1.printInfo();
            test1.shoot(Direction.DOWN,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.DOWN,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.DOWN,0);
            test1.printInfo();
            test1.shoot(Direction.DOWN,50);
            test1.printInfo();
            test1.move(Direction.DOWN);
        }
        
    }

    @Test
    void ShootUpRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,999999);
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,-99);
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,100/0);
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,4/3);
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,0);
            test1.printInfo();
            test1.shoot(Direction.UP_RIGHT,50);
            test1.printInfo();
            test1.move(Direction.UP_RIGHT);
        }
        
    }

    @Test
    void ShootUpLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,999999);
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,-99);
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,100/0);
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,4/3);
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,0);
            test1.printInfo();
            test1.shoot(Direction.UP_LEFT,50);
            test1.printInfo();
            test1.move(Direction.UP_LEFT);
        }
        
    }

    @Test
    void ShootDownLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,999999);
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,-99);
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,100/0);
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,4/3);
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,0);
            test1.printInfo();
            test1.shoot(Direction.DOWN_LEFT,50);
            test1.printInfo();
            test1.move(Direction.DOWN_LEFT);
        }
        
    }
    @Test
    void ShootDownRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,999999);
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,-99);
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,100/0);
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,4/3);
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,(99999999^99999999));
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,(99999999^99999999)*(-99));
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,0);
            test1.printInfo();
            test1.shoot(Direction.DOWN_RIGHT,50);
            test1.printInfo();
            test1.move(Direction.DOWN_RIGHT);
        }
        
    }
}
