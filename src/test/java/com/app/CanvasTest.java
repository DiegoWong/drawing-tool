package com.app;

import com.app.model.Canvas;
import com.app.model.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CanvasTest {


    Canvas canvas;

    @Before
    public void setUp() throws Exception {
        canvas = new Canvas(4, 4);
    }

    @Test
    public void testSetXY() throws Exception {
        canvas.setXY(1, 1, 't');
        assertEquals(canvas.getXY(1, 1), 't');
    }

    @Test
    public void testGetXY() throws Exception {
        canvas.setXY(1, 1, 't');
        assertEquals(canvas.getXY(1, 1), 't');
    }

    @Test
    public void testSetCoordinate() throws Exception {
        canvas.setCoordinate(new Coordinate(1, 1), 't');
        assertEquals(canvas.getXY(1, 1), 't');
    }

    @Test
    public void testGetCharFromCoordinate() throws Exception {
        Coordinate coordinate = new Coordinate(1, 1);
        canvas.setCoordinate(coordinate, 't');
        assertEquals(canvas.getCharFromCoordinate(coordinate), 't');
    }

    @Test
    public void testToString() throws Exception {
        String testCanvas =
                "------\n" +
                "|    |\n" +
                "|    |\n" +
                "|    |\n" +
                "|    |\n" +
                "------\n";
        assertEquals(testCanvas, canvas.toString());
    }
}