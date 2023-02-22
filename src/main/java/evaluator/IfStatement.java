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

    public Statement falseState() {
        return falseState;
    }

    @Override
    public long evaluate() throws SyntaxError {
        if (Expression().evaluate() > 0) {
            trueState.evaluate();
        } else {
            falseState.evaluate();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Ifstatement ");
        return  s;}
}
