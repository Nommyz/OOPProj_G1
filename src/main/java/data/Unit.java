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
    private long[] cityCenterPosition = new long[2];
    private final Set<Region> ownedRegions = new HashSet<>();
    private boolean isPlayerDone = false;
    private boolean isLose = false;
    private final Territory territory;

    public Unit(String name, Territory territory) {
        this.name = name;
        this.territory = territory;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        long spawnRow = rand.nextLong(territory.row());
        long spawnCol = rand.nextLong(territory.column());
        while (territory.region(new long[]{spawnRow, spawnCol}).getOwner() != null) {
            spawnRow = rand.nextLong(territory.row());
            spawnCol = rand.nextLong(territory.column());
        }
        position[0] = spawnRow;
        position[1] = spawnCol;
        cityCenterPosition[0] = position[0];
        cityCenterPosition[1] = position[1];
        territory.region(cityCenterPosition).setCityCenter(true);
        territory.region(cityCenterPosition).setOwner(this);
        territory.region(cityCenterPosition).setDeposit(Configuration.instance().init_center_dep);
        ownedRegions.add(territory.region(cityCenterPosition));
        this.budget = Configuration.instance().init_budget;
    }

    // for test
    public Unit(String name, Territory territory, int row, int column, int budget) {
        this.name = name;
        this.territory = territory;
        position[0] = row;
        position[1] = column;
        cityCenterPosition[0] = row;
        cityCenterPosition[1] = column;
        territory.region(cityCenterPosition).setCityCenter(true);
        territory.region(cityCenterPosition).setOwner(this);
        territory.region(cityCenterPosition).setDeposit(Configuration.instance().init_center_dep);
        ownedRegions.add(territory.region(cityCenterPosition));
        this.budget = budget;
    }

    public long[] getPosition() {
        long[] Position = this.position;
        return Position;
    }

    public long[] getCityCenterPosition() {
        long[] cityPosition = this.cityCenterPosition;
        return cityPosition;
    }

    public void lose() {
        isLose = true;
    }

    public void newTurn() {
        isPlayerDone = false;
    }

    public String getName() {
        return name;
    }

    public Set<Region> getOwnedRegions() {
        return ownedRegions;
    }

    public void move(Direction direction) {
        if (isPlayerDone || !cost(1) || isLose()) {
            return;
        }
        long[] toNextPosition = checkNextPosition(position, direction);
        if (isWithinBound(toNextPosition)) {
            position[0] = toNextPosition[0];
            position[1] = toNextPosition[1];
        }
        this.printUnitData();
    }

    public void randomMove() {
        if (isPlayerDone || isLose()) {
            return;
        }

        List<Direction> directions = Arrays.asList(UP, DOWN, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT);
        Collections.shuffle(directions);

        move(directions.get(0));
    }

    public long[] checkNextPosition(long[] currentPosition, Direction direction) {
        long[] nextPosition = currentPosition.clone();
        switch (direction) {
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

    public void invest(long amount) {
        if (isPlayerDone || isLose()) {
            return;
        }

        if (!cost(1)) {
            return;
        }
        if (amount < 0) {
            System.out.println("Can't invest amount < 0");
            return;
        }
        Region region = territory.region(position);
        if (region.getOwner() != null && region.getOwner() != this) {
            System.out.println(name + " can't invest in opponent region.");
            return;
        }

        if (!cost(amount)) {
            return;
        }
        region.updateInvestEvent(amount, this);
        System.out.println(name + " invested " + amount + " in region position (" + position[0] + "," + position[1] + ")");
        done();
    }


    public void collect(long amount) {
        if (isPlayerDone || isLose()) {
            return;
        }

        if (!cost(1)) {
            return;
        }
        if (amount < 0) {
            System.out.println("Can't collect amount < 0");
            return;
        }

        Region currentRegion = territory.region(position);
        if (currentRegion.getOwner() != this) {
            System.out.println(name + " can't collect because this unit not own this region.");
            return;
        }

        budget += currentRegion.updateCollectEvent(amount);
        done();
    }

    public void shoot(Direction direction, long amount) {
        if (isPlayerDone || isLose()) {
            return;
        }

        if (!cost(1)) {
            return;
        }

        if (!cost(amount)) {
            return;
        }

        long[] shootDirection = checkNextPosition(position, direction);
        if (!isWithinBound(shootDirection)) {
            return;
        }

        Region region = territory.region(shootDirection);
        if (region.getOwner() == null) {
            System.out.println(name + " can't shoot an unknowned region.");
            return;
        }

        region.gotShot(amount);
//        if (region.getDeposit() == 0 && region.isCenterCity()) {
//            Unit owner = region.getOwner();
//            owner.lose();
//            for (Region ownedRegion : owner.getOwnedRegions()) {
//                ownedRegion.setOwner(null);
//                ownedRegion.setCityCenter(false);
//            }
//            owner.getOwnedRegions().clear();
//        }

        done();
    }
    public boolean isLose() {
        if (isLose) {
            return true;
        }

        boolean noDeposit = territory.region(cityCenterPosition).getDeposit() == 0;
        boolean noBudget = this.budget == 0;

        if (noDeposit || noBudget) {
            for (Region region : ownedRegions) {
                region.setOwner(null);
                region.setCityCenter(false);
            }
            ownedRegions.clear();
            isLose = true;
            return true;
        } else {
            return false;
        }
    }
    public void relocate() {
        if (isPlayerDone || isLose()) {
            return;
        }

        long[] currentPosition = cityCenterPosition;
        int distance = 0;
        System.out.print("[" + cityCenterPosition[0] + "," + cityCenterPosition[1] + "]");

        while ((currentPosition[0] != position[0] && currentPosition[1] != position[1])
                || currentPosition[1] != position[1]
                || currentPosition[0] != position[0]) {
            if (currentPosition[0] == position[0]) {
                currentPosition = currentPosition[1] < position[1]
                        ? chooseDirection(currentPosition, UP_RIGHT, DOWN_RIGHT)
                        : chooseDirection(currentPosition, UP_LEFT, DOWN_LEFT);
            } else if (currentPosition[0] > position[0]) {
                if (currentPosition[1] == position[1]) {
                    currentPosition = checkNextPosition(currentPosition, UP);
                } else if (currentPosition[1] < position[1]) {
                    currentPosition = chooseDirection(currentPosition, UP, UP_RIGHT);
                } else {
                    currentPosition = chooseDirection(currentPosition, UP, UP_LEFT);
                }
            } else {
                if (currentPosition[1] == position[1]) {
                    currentPosition = checkNextPosition(currentPosition, DOWN);
                } else if (currentPosition[1] < position[1]) {
                    currentPosition = chooseDirection(currentPosition, DOWN, DOWN_RIGHT);
                } else {
                    currentPosition = chooseDirection(currentPosition, DOWN, DOWN_LEFT);
                }
            }
            distance++;
        }

        System.out.print("-> [" + currentPosition[0] + "," + currentPosition[1] + "]");
        System.out.println(" Distance: " + distance);

        if (cost(1) && cost(5 * distance + 10)) {
            if (territory.region(position).getOwner() == null) {
                long positionRegionDeposit = territory.region(position).getDeposit();
                long deposit = territory.region(cityCenterPosition).getDeposit();

                territory.region(cityCenterPosition).setDeposit(positionRegionDeposit);
                territory.region(position).setDeposit(deposit);

                this.removeRegion(territory.region(cityCenterPosition));
                territory.region(cityCenterPosition).setCityCenter(false);
                territory.region(cityCenterPosition).setOwner(null);

                cityCenterPosition = position;
                territory.region(cityCenterPosition).setOwner(this);
                territory.region(cityCenterPosition).setCityCenter(true);
                this.addRegion(territory.region(cityCenterPosition));
            } else {
                System.out.println("Region have the owner.");
            }
        }

        this.done();
    }

    private long[] chooseDirection(long[] currentPosition, Direction direction1, Direction direction2) {
        long[] nextPositionDirection1 = checkNextPosition(currentPosition, direction1);
        long[] nextPositionDirection2 = checkNextPosition(currentPosition, direction2);

        long distance1 = isWithinBound(nextPositionDirection1) ? Math.abs(position[1] - nextPositionDirection1[1]) + Math.abs(position[0] - nextPositionDirection1[0]) : Integer.MAX_VALUE;
        long distance2 = isWithinBound(nextPositionDirection2) ? Math.abs(position[1] - nextPositionDirection2[1]) + Math.abs(position[0] - nextPositionDirection2[0]) : Integer.MAX_VALUE;

        return distance1 < distance2 ? nextPositionDirection1 : nextPositionDirection2;
    }

    public int opponent() {
        long[] upDirection = position;
        long[] downDirection = position;
        long[] upleftDirection = position;
        long[] uprightDirection = position;
        long[] downleftDirection = position;
        long[] downrightDirection = position;
        if (territory.region(position).getOwner() == this) {
            for (int i = 1; i <= 6; i++) {
                upDirection = checkNextPosition(upDirection, UP);
                if (isOpponentRegion(upDirection))
                    return i * 10 + 1;
                downDirection = checkNextPosition(downDirection, DOWN);
                if (isOpponentRegion(downDirection))
                    return i * 10 + 4;
                upleftDirection = checkNextPosition(upleftDirection, UP_LEFT);
                if (isOpponentRegion(upleftDirection))
                    return i * 10 + 6;
                uprightDirection = checkNextPosition(uprightDirection, UP_RIGHT);
                if (isOpponentRegion(uprightDirection))
                    return i * 10 + 2;
                downleftDirection = checkNextPosition(downleftDirection, DOWN_LEFT);
                if (isOpponentRegion(downleftDirection))
                    return i * 10 + 5;
                downrightDirection = checkNextPosition(downrightDirection, DOWN_RIGHT);
                if (isOpponentRegion(downrightDirection))
                    return i * 10 + 3;
            }
        }
        return 0;
    }

    public long nearby(Direction direction) {
        long[] nearbyOpponent = position;
        for (int i = 1; i < Configuration.instance().m * Configuration.instance().n; i++) {
            nearbyOpponent = checkNextPosition(nearbyOpponent, direction);
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

    public void done() {
        System.out.println(this.name + " is done.");
        isPlayerDone = true;
    }

    public boolean cost(long cost) {
        if (cost < 0) {
            System.out.println("What cost < 0??");
            done();
            return false;
        }
        if (budget >= cost) {
            budget -= cost;
            return true;
        } else {
            System.out.println(this.name + " budget not enough.");
            done();
            return false;
        }
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

    public void printUnitData() {
        String message = String.format("Unit: %s | Budget: %d | Number of regions owned: %d", this.name, this.budget, ownedRegions.size());
        String positionMessage = String.format("Position: [%d, %d]", position[0], position[1]);
        System.out.println(message);
        System.out.println(positionMessage);
    }
}