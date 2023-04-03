package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ConfigurationController {
    @Autowired
    private Configuration configurationFile;

    @MessageMapping("/changeConfig")
    @SendTo("/topic/configurationFile")
    public Configuration changeConfig(MessageChangeConfig messageChangeConfig){
        return configurationFile.changeConfig(messageChangeConfig);
    }

    @MessageMapping("/setDefault")
    @SendTo("/topic/configurationFile")
    public Configuration setDefault(){
        return configurationFile.setDefault();
    }

    @SubscribeMapping("/configurationFile")
    public Configuration sendInitialConfig(){
        return configurationFile;
    }
}
