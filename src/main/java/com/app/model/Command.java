package com.app.model;

import lombok.Getter;


/**
 * Enumeration that contains allowed commands and command formating
 */
@Getter
public enum Command {
	CREATE("C", "C \\d+ \\d+"),
	DRAW_LINE("L", "L \\d+ \\d+ \\d+ \\d+"),
	DRAW_RECTANGLE("R", "R \\d+ \\d+ \\d+ \\d+"),
	FILL("B", "B \\d+ \\d+ ."),
	QUIT("Q", "Q");
	
	private final String value;
	private final String commandFormat;
	
	Command(String value, String commandFormat){
		this.value = value;
		this.commandFormat = commandFormat;
	}
}
