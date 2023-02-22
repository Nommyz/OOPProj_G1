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
    public long evaluate() throws SyntaxError {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }


}
