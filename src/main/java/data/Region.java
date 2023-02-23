package data;

public class Region {
    private final int[] position;
    private double deposit;
    private double r;
    private Unit owner;
    private boolean cityCenter;

    public Region(int x_position , int y_position, double start_deposit, double interest){
        this.position = new int[2];
        position[0] = x_position;
        position[1] = y_position;
        this.deposit = start_deposit;
        this.r = interest;
        this.owner = null;
        this.cityCenter = false;
    }
    public void setOwner(Unit player){
        this.owner = player;
    }

    public void setCenterCity(boolean b){
        this.cityCenter = b;
    }

    public void updateInterestRate(int turnCount , int b){
        this.r = b*(Math.log10(deposit))*Math.log(turnCount);
    }

    public void updateDeposit(){
        this.deposit = deposit+(deposit*r/100);
    }

    public void updateAfterInvest(int amount, Unit player, int max_dep){
        if(this.deposit + amount <= max_dep)
            this.deposit = this.deposit + amount;
        else
            this.deposit = max_dep;
        if(this.owner == null){
            this.setOwner(player);
        }
    }

    public void updateAfterCollect(int amount){
        if(amount - this.deposit < 0)
            return;
        else if(amount - this.deposit == 0){
            this.deposit = 0;
            this.setOwner(null);
        }
        else
            this.deposit = this.deposit - amount;
    }

    public void gotShot(int amount){
        if(this.owner != null){
            if(this.deposit - amount < 0){
                this.deposit = this.deposit - amount;
                this.setOwner(null);
            }
            else
                this.deposit = this.deposit - amount;
        }
        else
            return;
    }

    public void showInfo(){
        System.out.print("(" + this.position[0] + "," + this.position[1] + ")");
    }

    public int getDeposit(){
        int d = (int) this.deposit;
        return d;
    }

}
