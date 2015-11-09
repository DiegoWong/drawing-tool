package com.app.services;

import com.app.exceptions.InvalidCommandFormatException;
import com.app.exceptions.InvalidCoordinatesException;
import com.app.exceptions.NoCanvasException;
import com.app.model.Canvas;
import com.app.model.Command;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CommandValidatorTest {

    private CommandValidator commandValidator;
    private Canvas canvas;

    @Before
    public void setUp() throws Exception {
        HashMap<String, Command> commands = new HashMap<>();
        for (Command c : Command.values()){
            commands.put(c.getValue(), c);
        }
        commandValidator = new CommandValidator(commands);
        canvas = new Canvas(15, 15);
    }

    @Test
    public void testValidateCreateCommandValid() throws Exception {
        assertTrue(commandValidator.validateCreateCommand("C 1 5"));
    }

    @Test(expected = InvalidCommandFormatException.class)
    public void testValidateCreateCommandInvalidFormat() throws Exception {
        assertTrue(commandValidator.validateCreateCommand("C  1    5"));
    }

    @Test(expected = InvalidCommandFormatException.class)
    public void testValidateCreateCommandInvalidFormatWrongParameters() throws Exception {
        assertTrue(commandValidator.validateCreateCommand("C t x"));
    }

    @Test
    public void testValidateDrawLineCommand() throws Exception {
        assertTrue(commandValidator.validateDrawLineCommand("L 1 1 1 2", canvas));
    }

    @Test(expected = NoCanvasException.class)
    public void testValidateDrawLineCommandNoCanvas() throws Exception {
        assertTrue(commandValidator.validateDrawLineCommand("L 1 1 20 20", null));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void testValidateDrawLineCommandInvalidCoordinates() throws Exception {
        assertTrue(commandValidator.validateDrawLineCommand("L 1 1 20 20", canvas));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void testValidateDrawLineCommandDiagonalLine() throws Exception {
        assertTrue(commandValidator.validateDrawLineCommand("L 1 1 15 15", canvas));
    }

    @Test
    public void testValidateDrawRectangleCommand() throws Exception {
        assertTrue(commandValidator.validateDrawRectangleCommand("R 1 1 5 5", canvas));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void testValidateDrawRectangleCommandInvalidCoordinates() throws Exception {
        assertTrue(commandValidator.validateDrawRectangleCommand("R 1 1 50 50", canvas));
    }

    @Test(expected = NoCanvasException.class)
    public void testValidateDrawRectangleCommandNoCanvas() throws Exception {
        assertTrue(commandValidator.validateDrawRectangleCommand("R 1 1 5 5", null));
    }

    @Test
    public void testValidateFillCommand() throws Exception {
        assertTrue(commandValidator.validateFillCommand("B 1 1 .", canvas));
    }

    @Test(expected = InvalidCommandFormatException.class)
    public void testValidateFillCommandInvalidReplacingChar() throws Exception {
        assertTrue(commandValidator.validateFillCommand("B 1 1 5454", canvas));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void testValidateFillCommandInvalidCoordinates() throws Exception {
        assertTrue(commandValidator.validateFillCommand("B 20 20 .", canvas));
    }

    @Test(expected = NoCanvasException.class)
    public void testValidateFillCommandNoCanvas() throws Exception {
        assertTrue(commandValidator.validateFillCommand("B 1 1 .", null));
    }

}