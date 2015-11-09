package com.app;

import com.app.model.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


/**
 * Configuration class to manage beans
 */
@Configuration
@ComponentScan("com.app")
public class DrawingToolConfiguration {
	
	@Bean(name = "commands")
	public HashMap<String, Command> commands(){
		HashMap<String, Command> commands = new HashMap<String, Command>();
		for (Command c : Command.values()){
			commands.put(c.getValue(), c);
		}
		return commands;
	}

}
