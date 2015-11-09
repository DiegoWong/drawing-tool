package com.app;

import com.app.exceptions.InvalidCommandException;
import com.app.model.Command;
import com.app.services.CommandExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DrawingTool {

    private static Scanner scanner;
	private static ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException {
        applicationContext = new AnnotationConfigApplicationContext(DrawingToolConfiguration.class);
        scanner = new Scanner(System.in);
		CommandExecutor commandExecutor = applicationContext.getBean(CommandExecutor.class);
		StringBuilder validCommands = new StringBuilder();
        HashMap<String, Command> commands = (HashMap<String, Command>) applicationContext.getBean("commands");
		for (Command c : Command.values()){
			validCommands.append(c.getValue()).append(' ');
		}
        while (true){
			try{
				System.out.print("enter command: ");
				String commandString = scanner.nextLine();
				Command command = commands.get(commandString.substring(0, 1));
				if (command == null){
					throw new InvalidCommandException("Invalid Command, correct commands are " + validCommands.toString());
				}
				switch (command){
					case CREATE:
						commandExecutor.CreateCanvas(commandString);
						System.out.println(commandExecutor.getCanvas().toString());
						break;
					case DRAW_LINE:
						commandExecutor.drawLine(commandString);
						System.out.println(commandExecutor.getCanvas().toString());
						break;
					case DRAW_RECTANGLE:
						commandExecutor.drawRectangle(commandString);
						System.out.println(commandExecutor.getCanvas().toString());
						break;
					case FILL:
						commandExecutor.fill(commandString);
						System.out.println(commandExecutor.getCanvas().toString());
						break;
					case QUIT:
						System.exit(0);
						break;
					default:
						System.out.println("Invalid command");
						break;
				}
			} catch (RuntimeException exc){
				System.out.println(exc.getMessage());
			}

        	
        }

    }
}
