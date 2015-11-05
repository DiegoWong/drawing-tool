package com.app.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandValidator {

    List<String> commands;

    public CommandValidator(){
        commands = new ArrayList<String>();
        commands.add("C");
        commands.add("L");
        commands.add("R");
        commands.add("B");
        commands.add("Q");
    }

    public void validateCommand(String commandLine) {
        String command = commandLine.substring(0,1);
        commands.indexOf(command);
    }

}
