package evaluator;

public class IfStatement implements Statement{
    private Statement expression;
    private Statement trueState;
    private Statement falseState;

    public IfStatement(Statement expression, Statement trueState, Statement falseState) {
        this.trueState = trueState;
        this.expression = expression;
        this.falseState = falseState;
    }

    public Statement Expression() {
        return expression;
    }

    public Statement trueState() {
        return trueState;
    }

    public Statement StatementIfFalse() {
        return falseState;
    }

    @Override
    public String val() throws SyntaxError {
        return null;
    }
}
