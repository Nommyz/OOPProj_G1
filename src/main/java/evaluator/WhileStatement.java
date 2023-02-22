package evaluator;

public class WhileStatement implements Statement {
    private  Statement expression;
    private  Statement trueState;

    public WhileStatement(Statement expression, Statement trueState) {
        this.expression = expression;
        this.trueState = trueState;
    }

    public Statement Expression() {
        return expression;
    }

    public Statement trueState() {
        return trueState;
    }


    @Override
    public long evaluate() throws SyntaxError {
        for (int counter = 0; counter < 10000 && Expression().evaluate() > 0; counter++){
            trueState.evaluate();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Whilestatement ");
        return s;
    }
}
