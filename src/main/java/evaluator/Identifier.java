package evaluator;

public class Identifier implements Statement{
    private String val;

    public Identifier(String val) {
        this.val = val;
    }

    @Override
    public String val() throws SyntaxError {
        return val;
    }
}
