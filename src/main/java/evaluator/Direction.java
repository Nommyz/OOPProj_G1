package evaluator;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP("up"), UP_RIGHT("upright"), UP_LEFT("upleft"),
    DOWN("down"), DOWN_LEFT("downleft"),DOWN_RIGHT("downright"),;

    private final String dir;
    private static final Map<String, Direction> DIRECTION_MAP = new HashMap<>();

    Direction(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return dir;
    }

    static {
        DIRECTION_MAP.put(UP.dir, UP);
        DIRECTION_MAP.put(UP_RIGHT.dir, UP_RIGHT);
        DIRECTION_MAP.put(DOWN_RIGHT.dir, DOWN_RIGHT);
        DIRECTION_MAP.put(DOWN.dir, DOWN);
        DIRECTION_MAP.put(DOWN_LEFT.dir, DOWN_LEFT);
        DIRECTION_MAP.put(UP_LEFT.dir, UP_LEFT);
    }

    public static Direction getDirection(String direction) {
        return DIRECTION_MAP.getOrDefault(direction, null);
    }
}
