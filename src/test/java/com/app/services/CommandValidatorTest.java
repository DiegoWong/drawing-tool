package com.app.services;

import com.app.exceptions.InvalidCommandFormatException;
import com.app.model.Command;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CommandValidatorTest {

    CommandValidator commandValidator;

    @Before
    public void setUp() throws Exception {
        HashMap<String, Command> commands = new HashMap<>();
        for (Command c : Command.values()){
            commands.put(c.getValue(), c);
        }
        commandValidator = new CommandValidator(commands);
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

    }

    @Test
    public void testValidateDrawRectangleCommand() throws Exception {

    }

    @Test
    public void testValidateFillCommand() throws Exception {

    }
}