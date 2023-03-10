package data;

import java.lang.Math;

public class Region {
    private final long rowPosition;
    private final long columnPosition;
    private long deposit;
    private double interestRate;
    private Unit owner;
    private boolean isCenterCity;

    public Region(long rowPosition, long columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.deposit = Configuration.instance().start_deposit;
        this.interestRate = Configuration.instance().interest_pct;
        this.owner = null;
        this.isCenterCity = false;
    }

    public void setOwner(Unit player) {
        this.owner = player;
    }

    public void setCenterCityDeposit(int amount) {
        this.deposit = amount;
    }

    public void setCityCenter(boolean isCenterCity) {
        this.isCenterCity = isCenterCity;
    }

    public void updateInterestRate(int turnCount) {
        if (turnCount == 1) {
            this.interestRate = Configuration.instance().interest_pct;
        } else {
            this.interestRate = Configuration.instance().interest_pct * Math.log10(this.deposit) * Math.log(turnCount);
        }
    }

    public void updateDeposit() {
        this.deposit += this.deposit * this.interestRate / 100;
    }

    public void updateInvestEvent(long amount, Unit player) {
        long maxDeposit = Configuration.instance().max_dep;
        this.deposit = Math.min(this.deposit + amount, maxDeposit);
        if (this.owner == null) {
            this.setOwner(player);
            this.owner.addRegion(this);
        }
    }

    public long updateCollectEvent(long amount) {
        System.out.println(deposit);
        if (this.deposit - amount < 0)
            return 0;
        else if (this.deposit - amount == 0) {
            this.deposit = 0;
            this.interestRate = Configuration.instance().interest_pct;
            this.owner.removeRegion(this);
            this.setOwner(null);
            return amount;
        } else {
            this.deposit = this.deposit - amount;
            return amount;
        }
    }

    public void gotShot(long amount) {
        if (this.owner != null) {
            if (this.deposit - amount <= 0) {
                this.deposit = 0;
                this.interestRate = Configuration.instance().interest_pct;
                if (!isCenterCity) {
                    owner.removeRegion(this);
                    this.setOwner(null);
                }
            } else
                this.deposit = this.deposit - amount;
        }
    }

    public void printInfo() {
        System.out.print("(" + rowPosition + "," + columnPosition + ")");
        System.out.print(" Deposit : " + this.deposit);
        System.out.print(" Owner : " + this.owner);
        System.out.print(" Centercity : " + (isCenterCity ? this.owner : "no"));
        System.out.println(" ");
    }

    public long getDeposit() {
        return this.deposit;
    }

    public Unit getOwner() {
        return this.owner;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public boolean isCenterCity() {
        return this.isCenterCity;
    }

}