package evaluator;

public class Expr implements Statement {
    private Statement left, right;
    private String op;

    public Expr(Statement left, String op, Statement right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Statement Left() {
        return left;
    }

    public Statement Right() {
        return right;
    }

    public String Op() {
        return op;
    }


    @Override
    public long evaluate() throws SyntaxError {
        long lv = Left().evaluate();
        long rv = Right().evaluate();
        return switch (Op()) {
            case "+" -> lv + rv;
            case "-" -> lv - rv;
            case "*" -> lv * rv;
            case "/" -> lv / rv;
            case "%" -> lv % rv;
            case "^" -> (long) Math.pow(lv, rv);
            default -> throw new SyntaxError("Error");
        };
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Expression ");
        return s;
    }
}
