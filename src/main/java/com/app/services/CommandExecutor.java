package com.app.services;

import com.app.exceptions.InvalidCommandFormatException;
import com.app.exceptions.InvalidCoordinatesException;
import com.app.exceptions.NoCanvasException;
import com.app.model.Canvas;
import com.app.model.Coordinate;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@Component
@Getter
public class CommandExecutor {

    private Canvas canvas;
    private final CommandValidator commandValidator;

    @Autowired
    public CommandExecutor(CommandValidator commandValidator){
        this.commandValidator = commandValidator;
    }

    public void CreateCanvas(String command) throws InvalidCommandFormatException{
        commandValidator.validateCreateCommand(command);
        Scanner scanner = new Scanner(command);
        scanner.next();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.close();
        canvas = new Canvas(height, width);
    }

    public void drawLine(String command) throws InvalidCommandFormatException, InvalidCoordinatesException,
            NoCanvasException {
        commandValidator.validateDrawLineCommand(command, canvas);
        Scanner scanner = new Scanner(command);
        scanner.next();
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        Coordinate first = new Coordinate(x1,y1);
        Coordinate last = new Coordinate(x2,y2);
        drawLineByCoordinates(first, last);
        scanner.close();
    }

    public void drawRectangle(String command) {
        commandValidator.validateDrawRectangleCommand(command, canvas);
        Scanner scanner = new Scanner(command);
        scanner.next();
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        Queue<Coordinate> coordinates = new LinkedList<>();
        coordinates.add(new Coordinate(x1, y1));
        coordinates.add(new Coordinate(x2, y1));
        coordinates.add(new Coordinate(x2, y2));
        coordinates.add(new Coordinate(x1, y2));
        coordinates.add(new Coordinate(x1, y1));
        Coordinate previous = coordinates.poll();
        Coordinate next;
        while (!coordinates.isEmpty()){
            next = coordinates.poll();
            drawLineByCoordinates(previous, next);
            previous = next;
        }
    }

    public void fill(String command) {
        commandValidator.validateFillCommand(command, canvas);
        Scanner scanner = new Scanner(command);
        scanner.next();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        String replacingChar = scanner.next();
        Coordinate coordinate = new Coordinate(x, y);
        fillProcess(coordinate, canvas.getXY(x, y), replacingChar.charAt(0));

    }

    private void fillProcess(Coordinate startingCoordinate, char begginingChar, char endingChar){
        Queue<Coordinate> startingNodes = new LinkedList<>();
        Coordinate leftPoint;
        Coordinate rightpoint;
        if(canvas.getCharFromCoordinate(startingCoordinate) != begginingChar) {
            return;
        }
        startingNodes.add(startingCoordinate);
        while(!startingNodes.isEmpty()){
            Coordinate coordinate = startingNodes.poll();
            leftPoint = new Coordinate(coordinate);
            rightpoint = new Coordinate(coordinate);
            while(leftPoint.getX() - 1 > 0 && canvas.getXY(leftPoint.getX()-1, leftPoint.getY()) == begginingChar){
                leftPoint.moveLeft();
            }
            while(rightpoint.getX() < canvas.getWidth()  &&
                    canvas.getXY(rightpoint.getX() + 1, rightpoint.getY()) == begginingChar){
                rightpoint.moveRight();
            }

            fillLine(leftPoint, rightpoint, begginingChar, endingChar, startingNodes);
        }

    }

    private void fillLine(Coordinate first, Coordinate last, char begginingChar, char endingChar,
                          Queue<Coordinate> startingPoints){
        Coordinate aux;
        if(first.getX() > last.getX() || first.getY() >last.getY()){
            aux = first;
            first = last;
            last = aux;
        }
        for(int i = first.getX() ; i <= last.getX(); i++ ){
            for (int j = first.getY(); j <=last.getY(); j++){
                canvas.setXY(i, j, endingChar);
                if(j+1 <= canvas.getHeight() && canvas.getXY(i, j+1) == begginingChar){
                    startingPoints.add(new Coordinate(i, j + 1));
                }
                if(j-1 > 0 && canvas.getXY(i, j-1) == begginingChar){
                    startingPoints.add(new Coordinate(i, j - 1));
                }
            }
        }
    }

    private void drawLineByCoordinates(Coordinate first, Coordinate last){
        Coordinate aux;
        if(first.getX() > last.getX() || first.getY() >last.getY()){
            aux = first;
            first = last;
            last = aux;
        }
        for(int i = first.getX() ; i <= last.getX(); i++ ){
            for (int j = first.getY(); j <=last.getY(); j++){
                canvas.setXY(i, j, 'x');
            }
        }
    }
}
