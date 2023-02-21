package evaluator;

public class IntLit implements Statement {
    private int val;

    public IntLit(int val) {
        this.val = val;
    }

    public int eval() {
        return val;
    }

    @Override
    public String val() throws SyntaxError {
        return Integer.toString(val);
    }


}
