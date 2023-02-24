package data;

public class Territory  {
    int m ;
    int n;
    public Region[][] Territory;

    public Territory(int m , int n , double deposit , double interest){
        this.m = m;
        this.n = n;
        Region[][] Territory = new Region[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                Territory[i][j] = new Region(i,j,deposit,interest);
            }
        }
        this.Territory = Territory;
    }

    public 

}
