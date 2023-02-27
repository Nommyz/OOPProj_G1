package evaluator;

import data.Unit;


public class testParser {
    public static void main(String arg[]) throws SyntaxError {
        String s = null;
        Unit u = new Unit("jame");
        Tokenizer t = new Tokenizer("t = 5 move up"); // for watch token
        System.out.println(t.tokenlist);
        Parser p = new Parser();
        try {
            s = p.parse("t = 5 move up", u);
        } catch (SyntaxError e) {

        }

        System.out.println("Commandlist = " + s);
        System.out.println(p.variableStorage.variables);
    }
}
