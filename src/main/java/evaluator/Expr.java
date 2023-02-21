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
    public String val() throws SyntaxError {
        return op;
    }
}
