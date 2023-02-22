package evaluator;

import data.Unit;

public class InfoExpr implements Statement {
    private String arg;
    private Direction direction;
    private Unit crew ;
    public InfoExpr(String command,Unit crew) {
        this.arg = command;
        this.crew = crew;
    }

    public InfoExpr(String command, Direction direction, Unit crew) {
        this.arg = command;
        this.direction = direction;
        this.crew = crew;
    }

    public String Command() {
        return arg;
    }

    public Direction Direction() {
        return direction;
    }

    @Override
    public long evaluate() throws SyntaxError {
        // todo
        return switch (Command()) {
            case "opponent" -> crew.getOpponent();
            case "nearby" -> crew.nearBy(Direction());
            default -> throw new SyntaxError("Error");
        };
    }


    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("InfoExpr ");
        return s;
    }


}
