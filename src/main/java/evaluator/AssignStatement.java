package evaluator;

public class AssignStatement implements Statement {
    private Statement identifier;
    private String op;
    private Statement expression;

    public AssignStatement(Statement identifier, String op, Statement expression) {
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
    }

    public Statement Identifier() {
        return identifier;
    }

    public String Op() {
        return op;
    }

    public Statement Expression() {
        return expression;
    }

    @Override
    public String val() throws SyntaxError {
        return null;
    }

}
