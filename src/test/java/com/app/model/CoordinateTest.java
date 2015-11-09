package com.app.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

    Coordinate coordinate;

    @Before
    public void setUp() throws Exception {
        coordinate = new Coordinate(3, 3);
    }

    @Test
    public void testMoveLeft() throws Exception {
        coordinate.moveLeft();
        assertEquals(coordinate.getX(), 2);
    }

    @Test
    public void testMoveRight() throws Exception {
        coordinate.moveRight();
        assertEquals(coordinate.getX(), 4);
    }

    @Test
    public void testMoveUp() throws Exception {
        coordinate.moveUp();
        assertEquals(coordinate.getY(), 2);
    }

    @Test
    public void testMoveDown() throws Exception {
        coordinate.moveDown();
        assertEquals(coordinate.getY(), 4);
    }
}