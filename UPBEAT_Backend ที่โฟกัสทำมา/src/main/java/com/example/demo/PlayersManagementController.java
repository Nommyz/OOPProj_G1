package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayersManagementController {
    @Autowired
    private PlayersManagement playersManagement;

    @MessageMapping("/enterName")
    @SendTo("/topic/playersManagement")
    public PlayersManagement enterName(MessageEnterName messageEnterName){
        return playersManagement.enterName(messageEnterName);
    }

    @MessageMapping("/setFromConfig")
    @SendTo("/topic/playersManagement")
    public PlayersManagement setFromConfig(){
        return playersManagement.setFromConfig();
    }

    @SubscribeMapping("/playersManagement")
    public PlayersManagement sendInitialPlayers(){
        return playersManagement;
    }
}
