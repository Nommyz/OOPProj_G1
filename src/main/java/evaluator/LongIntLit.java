package evaluator;

public class LongIntLit implements Statement {
    private int val;

    public LongIntLit(int val) {
        this.val = val;
    }

    public int eval() {
        return val;
    }

    @Override
    public long evaluate() throws SyntaxError {
        return val;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("LongIntlit ");
        return s;
    }


}
