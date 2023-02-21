package evaluator;

public class InfoExpr implements Statement {
    private String arg;
    private Direction direction;

    public InfoExpr(String command) {
        this.arg = command;
    }

    public InfoExpr(String command, Direction direction) {
        this.arg = command;
        this.direction = direction;
    }

    public String Command() {
        return arg;
    }

    public Direction Direction() {
        return direction;
    }

    @Override
    public String val() throws SyntaxError {
        if (direction == null) {
            return arg;
        } else {
            return arg + " " + direction;
        }
    }


}
