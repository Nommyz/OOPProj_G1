package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TerritoryController {
    @Autowired
    private Territory territory;

    @SubscribeMapping("/territory")
    public Territory sendInitialTerritory(){
        return territory;
    }
}
