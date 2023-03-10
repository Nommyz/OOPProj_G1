package data;

import evaluator.Direction;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static evaluator.Direction.*;

public class Unit {
    private final String name;
    private long budget;
    protected Map<String, Long> variables;
    private final long[] position = new long[2];
    private final long[] cityPosition = new long[2];
    private final Set<Region> ownedRegions = new HashSet<>();
    private boolean isPlayerDone = false;
    private boolean isLose = false;
    private final Territory territory;

    public Unit(String name, Territory territory) {
        this.name = name;
        this.territory = territory;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        long startRow = rand.nextLong(territory.row());
        long startColumn = rand.nextLong(territory.column());
        while (territory.region(new long[]{startRow, startColumn}).getOwner() != null) {
            startRow = rand.nextLong(territory.row());
            startColumn = rand.nextLong(territory.column());
        }
        position[0] = startRow;
        position[1] = startColumn;
        cityPosition[0] = position[0];
        cityPosition[1] = position[1];
        territory.region(cityPosition).setCityCenter(true);
        territory.region(cityPosition).setOwner(this);
        territory.region(cityPosition).setCenterCityDeposit(Configuration.instance().init_center_dep);
        ownedRegions.add(territory.region(cityPosition));
        this.budget = Configuration.instance().init_budget;
    }

    // for test
    public Unit(String name, Territory territory, int row, int column, int budget) {
        this.name = name;
        this.territory = territory;
        position[0] = row;
        position[1] = column;
        cityPosition[0] = row;
        cityPosition[1] = column;
        territory.region(cityPosition).setCityCenter(true);
        territory.region(cityPosition).setOwner(this);
        territory.region(cityPosition).setCenterCityDeposit(Configuration.instance().init_center_dep);
        ownedRegions.add(territory.region(cityPosition));
        this.budget = budget;
    }

    public void move(Direction direction) {
        if (isPlayerDone || !pay(1)) {
            return;
        }
        long[] nextPosition = nextPosition(position, direction);
        if (isWithinBound(nextPosition)) {
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        }
        this.printInfo();
    }

    public void randomMove() {
        if (isPlayerDone) {
            return;
        }

        List<Direction> directions = Arrays.asList(UP, DOWN, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT);
        Collections.shuffle(directions);

        move(directions.get(0));
    }

    public void invest(long amount) {
        if (isPlayerDone) {
            return;
        }

        if (!pay(1)) {
            return;
        }

        Region region = territory.region(position);
        if (region.getOwner() != null && region.getOwner() != this) {
            System.out.println(name + " can't invest on this region because it was occupied by opponent.");
            return;
        }

        if (!pay(amount)) {
            return;
        }

        region.updateInvestEvent(amount, this);
        System.out.println(name + " invested " + amount + " in region position (" + position[0] + "," + position[1] + ")");
        this.done();
    }


    public void collect(long amount) {
        if (isPlayerDone) {
            return;
        }

        if (!pay(1)) {
            return;
        }

        Region currentRegion = territory.region(position);
        if (currentRegion.getOwner() != this) {
            System.out.println(name + " can't collect because they don't own this region.");
            return;
        }

        budget += currentRegion.updateCollectEvent(amount);
        done();
    }

    public void shoot(Direction direction, long amount) {
        if (isPlayerDone) {
            return;
        }

        if (!pay(1)) {
            return;
        }

        if (!pay(amount)) {
            return;
        }

        long[] shootDirection = nextPosition(position, direction);
        if (!isWithinBound(shootDirection)) {
            return;
        }

        Region region = territory.region(shootDirection);
        if (region.getOwner() == null) {
            System.out.println(name + " can't shoot an unowned region.");
            return;
        }

        region.gotShot(amount);
        if (region.getDeposit() == 0 && region.isCenterCity()) {
            Unit owner = region.getOwner();
            owner.lose();
            for (Region ownedRegion : owner.getOwnedRegions()) {
                ownedRegion.setOwner(null);
                ownedRegion.setCityCenter(false);
            }
            owner.getOwnedRegions().clear();
        }

        done();
    }

    public long nearby(Direction direction) {
        long[] nearbyOpponent = position;
        for (int i = 1; i < Configuration.instance().m * Configuration.instance().n; i++) {
            nearbyOpponent = nextPosition(nearbyOpponent, direction);
            if (isOpponentRegion(nearbyOpponent)) {
                long currentdeposit = territory.region(nearbyOpponent).getDeposit();
                int depositDigits = 0;
                while (currentdeposit != 0) {
                    currentdeposit /= 10;
                    depositDigits++;
                }
                return 100L * i + depositDigits;
            }
        }
        return 0;
    }
    public int opponent() {
        Direction[] directions = {UP, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};
        int maxDistance = 6;
        for (int i = 1; i <= maxDistance; i++) {
            for (Direction direction : directions) {
                long[] nextPos = searchPosition(i,position, direction);
                if (isWithinBound(nextPos) && territory.region(nextPos).getOwner() != this &&  isOpponentRegion(nextPos)) {
                    if (direction.equals(UP)) return i * 10 + 1;
                    if (direction.equals(UP_RIGHT)) return i * 10 + 2;
                    if (direction.equals(DOWN_RIGHT)) return i * 10 + 3;
                    if (direction.equals(DOWN)) return i * 10 + 4;
                    if (direction.equals(DOWN_LEFT)) return i * 10 + 5;
                    if (direction.equals(UP_LEFT)) return i * 10 + 6;
                }
            }
        }
        return 0;
    }


    public void done() {
        System.out.println(this.name + " is done.");
        isPlayerDone = true;
    }

    public boolean pay(long cost) {
        if (budget >= cost) {
            budget -= cost;
            return true;
        } else {
            System.out.println(this.name + " doesn't have enough budget to execute this command.");
            done();
            return false;
        }
    }

    public boolean isLose() {
        return isLose;
    }

    private boolean isWithinBound(long[] position) {
        boolean rowInRange = position[0] >= 0 && position[0] < territory.row();
        boolean colInRange = position[1] >= 0 && position[1] < territory.column();
        return rowInRange && colInRange;
    }

    private boolean isOpponentRegion(long[] position) {
        if (!isWithinBound(position)) {
            return false;
        }
        Unit owner = territory.region(position).getOwner();
        return owner != null && owner != this;
    }

    public Map<String, Long> getVariable() {
        return variables;
    }

    public void removeRegion(Region r) {
        ownedRegions.remove(r);
    }

    public void addRegion(Region r) {
        ownedRegions.add(r);
    }

    public void printInfo() {
        String message = String.format("Name: %s | Budget: %d | Number of regions owned: %d", this.name, this.budget, ownedRegions.size());
        String positionMessage = String.format("Position: (%d, %d)", position[0], position[1]);
        System.out.println(message);
        System.out.println(positionMessage);
    }

    public long[] getPosition() {
        long[] Position = this.position;
        return Position;
    }

    public long[] getCityPosition() {
        long[] cityPosition = this.cityPosition;
        return cityPosition;
    }

    public void lose() {
        isLose = true;
    }

    public void newTurn() {
        isPlayerDone = false;
    }

    public Set<Region> getOwnedRegions() {
        return ownedRegions;
    }
    public long[] nextPosition(long[] currentPosition, Direction direction) {
        long[] nextPosition = currentPosition.clone();
        switch(direction) {
            case UP:
                nextPosition[0]--;
                break;
            case DOWN:
                nextPosition[0]++;
                break;
            case UP_RIGHT:
                nextPosition[0] -= currentPosition[1] % 2 == 0 ? 0 : 1;
                nextPosition[1]++;
                break;
            case UP_LEFT:
                nextPosition[0] -= currentPosition[1] % 2 == 0 ? 0 : 1;
                nextPosition[1]--;
                break;
            case DOWN_RIGHT:
                nextPosition[0] += currentPosition[1] % 2 != 0 ? 0 : 1;
                nextPosition[1]++;
                break;
            case DOWN_LEFT:
                nextPosition[0] += currentPosition[1] % 2 != 0 ? 0 : 1;
                nextPosition[1]--;
                break;
            default:
                break;
        }
        return nextPosition;
    }
    // need to fix
    public long[] searchPosition(int distance,long[] currentPosition, Direction direction) {
        long[] nextPosition = Arrays.copyOf(currentPosition, 2);

        switch(direction) {
            case UP:
                nextPosition[0]-= distance;
                break;
            case DOWN:
                nextPosition[0]+= distance;
                break;
            case UP_RIGHT:
                nextPosition[0] -= currentPosition[1] % 2 == 0 ? 0 : distance;
                nextPosition[1]++;
                break;
            case UP_LEFT:
                nextPosition[0] -= currentPosition[1] % 2 == 0 ? 0 : distance;
                nextPosition[1]--;
                break;
            case DOWN_RIGHT:
                nextPosition[0] += currentPosition[1] % 2 != 0 ? 0 : distance;
                nextPosition[1]++;
                break;
            case DOWN_LEFT:
                nextPosition[0] += currentPosition[1] % 2 != 0 ? 0 : distance;
                nextPosition[1]--;
                break;
            default:
                break;
        }
        return nextPosition;
    }

}