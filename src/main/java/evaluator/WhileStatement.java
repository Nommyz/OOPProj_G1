package evaluator;

public class WhileStatement implements Statement {
    private  Statement expression;
    private  Statement trueState;

    public WhileStatement(Statement expression, Statement trueState) {
        this.expression = expression;
        this.trueState = trueState;
    }

    public Statement getExpression() {
        return expression;
    }

    public Statement trueState() {
        return trueState;
    }
    @Override
    public String val() throws SyntaxError {
        return null;
    }


}
