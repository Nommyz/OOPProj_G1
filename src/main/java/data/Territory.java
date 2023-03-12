package data;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final int m;
    private final int n;
    private final List<Region> regions; // use list to store fucking region

    public Territory() {
        this.m = Configuration.instance().m;
        this.n = Configuration.instance().n;
        this.regions = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                regions.add(new Region(i, j));
            }
        }
    }

    public Region region(long[] position) {
        int row = (int) position[0];
        int col = (int) position[1];
        int index = row * n + col;
        return regions.get(index);
    }

    public void printTerritoryData() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                regions.get(index).printRegionData();
            }
            System.out.println("-----------------------------");
        }
    }

    public int row() {
        return m;
    }

    public int column() {
        return n;
    }
}