package evaluator;

import java.util.Map;

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
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
