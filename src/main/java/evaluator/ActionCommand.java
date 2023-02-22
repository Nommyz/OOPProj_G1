package evaluator;

import data.Unit;

public class ActionCommand implements Statement{
    private String condition;
    private Direction direction;
    private Statement expression;
    private Unit crew;
    private boolean check1,check2;
    public ActionCommand(String action, Direction direction,Unit crew) {
        this.condition = action;
        this.direction = direction;
        this.crew = crew;
        check1 = true;
    }
    public ActionCommand(String action, Statement expression) {
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
    public long evaluate() throws SyntaxError {
        if(check1){
            if (ActionCmd().equals("move")) {

                crew.move(Direction());

            } else if (ActionCmd().equals("shoot")) {

                crew.shoot(Direction());

            } else {
                throw new SyntaxError("Error");
            }
        }else{

        }

        return 0;
    }
    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
