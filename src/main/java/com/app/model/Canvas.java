package com.app.model;

public class Canvas {

    private final char[][] drawingArea;

    public Canvas(int height, int length) {

        drawingArea = new char[length][height];

    }
}
