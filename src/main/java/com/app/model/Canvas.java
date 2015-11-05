package com.app.model;

import lombok.Getter;

@Getter
public class Canvas {

    private final char[][] drawingArea;
    private final int height;
    private final int length;

    public Canvas(int height, int length) {

        this.length = length;
        this.height = height;
        drawingArea = new char[length][height];
    }
}
