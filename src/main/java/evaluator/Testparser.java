package evaluator;

import data.Territory;
import data.Unit;
import org.testng.annotations.Test;

public class Testparser {
    @Test
    void MoveUpTest() throws SyntaxError {
        Parser p = new Parser();
        Territory t = new Territory();
        Unit crew = new Unit("Jame",t);
        crew.printUnitData();
        p.parse("move up invest 100",crew);
    }

}
