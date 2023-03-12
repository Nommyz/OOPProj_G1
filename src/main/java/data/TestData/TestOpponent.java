package data.TestData;

import data.Territory;
import data.Unit;
import org.testng.annotations.Test;

public class TestOpponent {
    @Test
    void TestOppo(){
        Territory territory = new Territory();
        Unit test1 = new Unit("test1", territory, 5, 5, 1000);
        Unit test2 = new Unit("test2", territory, 6, 5, 1000);
        Unit test3 = new Unit("test3", territory, 6, 6, 1000);
        Unit test4 = new Unit("test4", territory, 8, 5, 1000);
        Unit test5 = new Unit("test5", territory, 12, 5, 1000);
        Unit test6 = new Unit("test6", territory, 10, 5, 1000);
        System.out.println(test1.opponent());
        System.out.println(test2.opponent());
        System.out.println(test3.opponent());
        System.out.println(test4.opponent());
        System.out.println(test5.opponent());
        System.out.println(test6.opponent());
    }
}
