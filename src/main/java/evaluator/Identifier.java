package evaluator;

import java.util.Map;
import java.util.Random;

public class Identifier implements Statement{
    protected Map<String, Long> variables;
    protected String val;

    public Identifier(String val) {
        this.val = val;
    }
    public String value() throws SyntaxError {
        return val;
    }

    @Override
    public long evaluate() throws SyntaxError {
        Random random = new Random();
        long MAX_RAND_BOUND = 1000;
        String varName = val;
        if (varName.equals("random")) {
            return random.nextLong(MAX_RAND_BOUND);
        } else if (variables.containsKey(varName)) {
            return variables.get(varName);
        } else {
            throw new SyntaxError("Error");
        }

    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Identifiercalled ");
        return s;
    }
}
