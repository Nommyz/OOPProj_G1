package evaluator;

import data.Unit;

public class ActionCommand implements Statement {
    private String action;
    private Direction direction;
    private Statement expression;
    private Unit crew;
    private boolean check1 = false, check2 = false;

    public ActionCommand(String action, Direction direction, Unit crew) {
        this.action = action;
        this.direction = direction;
        this.crew = crew;
        check1 = true;
    }

    public ActionCommand(String action, Statement expression,Unit crew) {
        this.action = action;
        this.expression = expression;
        this.crew = crew;
        check2 = true;
    }
    public ActionCommand(String action,Direction direction,Statement expression,Unit crew) {
        this.action = action;
        this.expression = expression;
        this.direction = direction;
        this.crew = crew;
        check2 = true;
    }

    public ActionCommand(String action,Unit crew) {
        this.action = action;
        this.crew = crew;
    }

    @Override
    public long evaluate() throws SyntaxError {
        switch (action) {
            case "move" -> crew.move(direction);
            case "shoot" ->  crew.shoot(direction,expression.evaluate());
            case "invest" ->  crew.invest(expression.evaluate());
            case "collect" -> crew.collect(expression.evaluate());
            case "done" -> crew.done();
            case "relocate" -> crew.relocate();
            default -> throw new SyntaxError("Error");
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("ActionStatement: " + direction + " ");
        return s;
    }
}
