package com.app.model;

import lombok.Getter;

import java.util.Arrays;

/**
 * Model class to represent a canvas
 */
@Getter
public class Canvas {

    private final char[][] drawingArea;
    private final int height;
    private final int width;
    private final String line;

    public Canvas(int height, int width) {

        this.height = height;
        this.width = width;
        drawingArea = new char[height][width];
        fillDrawingArea();
        line = createLine();
    }

    public void setXY(int x, int y, char fill){
        drawingArea[y-1][x-1] = fill;
    }

    public char getXY(int x, int y){
        return drawingArea[y-1][x-1];
    }

    public void setCoordinate(Coordinate coordinate, char fill){
        drawingArea[coordinate.getY()-1][coordinate.getX()-1] = fill;
    }

    public char getCharFromCoordinate(Coordinate coordinate){
        return drawingArea[coordinate.getY()-1][coordinate.getX()-1];
    }

    /**
     * the toString method is overloaded to display then content of the canvas in a formatted way
     *
     * @return formated string to represent the canvas
     */
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(line);
        stringBuilder.append('\n');
        for (char[] aDrawingArea : drawingArea) {
            stringBuilder.append('|').append(new String(aDrawingArea)).append('|').append('\n');
        }
        stringBuilder.append(line);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    private String createLine(){
        char[] line = new char[width + 2];
        Arrays.fill(line, '-');
        return new String(line);
    }

    private void fillDrawingArea(){
        for (char[] aDrawingArea : drawingArea) {
            Arrays.fill(aDrawingArea, ' ');
        }
    }
}
