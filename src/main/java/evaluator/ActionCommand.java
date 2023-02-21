package evaluator;

public class ActionCommand implements Statement{
    private String condition;
    private Direction direction;
    private Statement expression;
    public ActionCommand(String action, Direction direction) {
        this.condition = action;
        this.direction = direction;
    }
    public ActionCommand(String action, Statement expression ) {
        this.condition= action;
        this.expression = expression;
    }
    public String ActionCmd() {
        return condition;
    }

    public Direction  Direction() {
        return direction;
    }
    @Override
    public String val() throws SyntaxError {
        return null;
    }
}
