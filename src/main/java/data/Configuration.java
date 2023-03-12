package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    public int m;
    public int n;
    public long init_plan_min;
    public long init_plan_sec;
    public long init_budget;
    public int init_center_dep;
    public int plan_rev_min;
    public int plan_rev_sec;
    public int rev_cost;
    public int max_dep;
    public int interest_pct;
    public int start_deposit;

    private static Configuration instance;

    private Configuration() {
        Properties props = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/main/java/configuration/configuration.txt");
            props.load(input);
            input.close();
        } catch (IOException e) {
            System.err.println("Error read file :" + e.getMessage());
            return;
        }
        m = Integer.parseInt(props.getProperty("m"));
        n = Integer.parseInt(props.getProperty("n"));
        init_plan_min = Long.parseLong(props.getProperty("init_plan_min"));
        init_plan_sec = Long.parseLong(props.getProperty("init_plan_sec"));
        init_budget = Long.parseLong(props.getProperty("init_budget"));
        init_center_dep = Integer.parseInt(props.getProperty("init_center_dep"));
        plan_rev_min = Integer.parseInt(props.getProperty("plan_rev_min"));
        plan_rev_sec = Integer.parseInt(props.getProperty("plan_rev_sec"));
        rev_cost = Integer.parseInt(props.getProperty("rev_cost"));
        max_dep = Integer.parseInt(props.getProperty("max_dep"));
        interest_pct = Integer.parseInt(props.getProperty("interest_pct"));
        start_deposit = Integer.parseInt(props.getProperty("start_deposit"));
    }

    public static Configuration instance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
}