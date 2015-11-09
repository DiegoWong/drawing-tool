package com.app.services;

import com.app.exceptions.InvalidCommandFormatException;
import com.app.exceptions.InvalidCoordinatesException;
import com.app.exceptions.NoCanvasException;
import com.app.model.Canvas;
import com.app.model.Command;
import com.app.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Component used for validate the entered commands
 */
@Component
public class CommandValidator {

    private final HashMap<String, Command> commands;

    @Autowired
    public CommandValidator(HashMap<String, Command> commands){
        this.commands = commands;
    }

    public boolean validateCreateCommand(String command) throws InvalidCommandFormatException{
        validateCommandFormat(command);
        return true;
    }
    public boolean validateDrawLineCommand(String command, Canvas canvas) throws  InvalidCommandFormatException{
        validateNoCanvas(canvas);
        validateCommandFormat(command);
        Scanner scanner = new Scanner(command);
        scanner.next();
        Coordinate first = getCoordinateFromScanner(scanner);
        Coordinate last = getCoordinateFromScanner(scanner);
        validateCoordinates(canvas, first);
        validateCoordinates(canvas, last);
        validateDiagonalLine(first, last);
        scanner.close();
        return true;
    }

    public boolean validateDrawRectangleCommand(String command, Canvas canvas) {
        validateNoCanvas(canvas);
        validateCommandFormat(command);
        Scanner scanner = new Scanner(command);
        scanner.next();
        Coordinate first = getCoordinateFromScanner(scanner);
        Coordinate last = getCoordinateFromScanner(scanner);
        validateCoordinates(canvas, first);
        validateCoordinates(canvas, last);
        return true;
    }

    public boolean validateFillCommand(String command, Canvas canvas) {
        validateNoCanvas(canvas);
        validateCommandFormat(command);
        Scanner scanner = new Scanner(command);
        scanner.next();
        Coordinate coordinate = getCoordinateFromScanner(scanner);
        validateCoordinates(canvas, coordinate);
        return true;
    }

    private Coordinate getCoordinateFromScanner(Scanner scanner){
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Coordinate(x, y);
    }

    private void validateCommandFormat(String command) throws InvalidCommandFormatException{
        if(!command.matches(commands.get(command.substring(0,1)).getCommandFormat())){
            throw new InvalidCommandFormatException("Invalid Command formatting, valid format is " +
                    commands.get(command.substring(0,1)).getCommandFormat());
        }
    }

    private void validateNoCanvas(Canvas canvas) throws NoCanvasException{
        if(canvas == null){
            throw new NoCanvasException("there isn't a canvas to draw, please execute run command first");
        }
    }

    private void validateCoordinates(Canvas canvas, Coordinate coordinate){
        if(canvas.getWidth() < coordinate.getX() && canvas.getHeight() < coordinate.getY()
                && coordinate.getX() > 0 && coordinate.getY() > 0){
            throw new InvalidCoordinatesException("Invalid coordinates in command, " +
                    "the coordinates can't be bigger than the canvas size or begin with 0");
        }
    }

    private void validateDiagonalLine(Coordinate first, Coordinate last) throws InvalidCoordinatesException{
        if(!(first.getX()==last.getX() || first.getY()==last.getY())){
            throw new InvalidCoordinatesException("Invalid coordinates in command, " +
                    "diagonal lines not valid for this version");
        }
    }
}
