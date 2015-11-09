package com.app.services;

import com.app.model.Canvas;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CommandExecutorTest {

    CommandExecutor commandExecutor;

    @Mock
    CommandValidator commandValidator;

    @Before
    public void setUp(){
        commandExecutor = new CommandExecutor(commandValidator);
        when(commandValidator.validateCreateCommand(anyString())).thenReturn(true);
        when(commandValidator.validateDrawLineCommand(anyString(), any(Canvas.class))).thenReturn(true);
    }

    @Test
    public void testCreateCanvas() throws Exception {
        String createCanvasTestString =
                "-----\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "-----\n";
        commandExecutor.CreateCanvas("C 3 3");
        assertEquals(commandExecutor.getCanvas().toString(), createCanvasTestString);
    }

    @Test
    public void testDrawLine() throws Exception {
        String testDrawLine =
                "-----------------\n" +
                "|               |\n" +
                "|xxxxxxxxxxxxxxx|\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "-----------------\n";
        commandExecutor.CreateCanvas("C 15 15");
        commandExecutor.drawLine("L 1 2 15 2");
        assertEquals(commandExecutor.getCanvas().toString(), testDrawLine);
    }

    @Test
    public void testDrawRectangle() throws Exception {
        String testDrawRectangle =
                "-----------------\n" +
                "|               |\n" +
                "|               |\n" +
                "|  xxxxxxxxxx   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  x        x   |\n" +
                "|  xxxxxxxxxx   |\n" +
                "|               |\n" +
                "|               |\n" +
                "|               |\n" +
                "-----------------\n";
        commandExecutor.CreateCanvas("C 15 15");
        commandExecutor.drawRectangle("R 3 3 12 12");
        assertEquals(commandExecutor.getCanvas().toString(), testDrawRectangle);

    }

    @Test
    public void testFillAllCanvas() throws Exception {
        String testFillAllCanvas =
                "------------\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "------------\n";
        commandExecutor.CreateCanvas("C 10 10");
        commandExecutor.fill("B 3 3 .");
        assertEquals(commandExecutor.getCanvas().toString(), testFillAllCanvas);

    }

    @Test
    public void testFillBottomHalfCanvas() throws Exception {
        String testBottomHalfFill =
                "--------------\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|xxxxxxxxxxxx|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "--------------\n";
        commandExecutor.CreateCanvas("C 12 12");
        commandExecutor.drawLine("L 1 6 12 6");
        commandExecutor.fill("B 10 10 .");
        assertEquals(commandExecutor.getCanvas().toString(), testBottomHalfFill);
    }

    @Test
    public void testFillUpperHalfCanvas() throws Exception {
        String testUpperHalfFill =
                "--------------\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|............|\n" +
                "|xxxxxxxxxxxx|\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "|            |\n" +
                "--------------\n";
        commandExecutor.CreateCanvas("C 12 12");
        commandExecutor.drawLine("L 1 6 12 6");
        commandExecutor.fill("B 1 1 .");
        assertEquals(commandExecutor.getCanvas().toString(), testUpperHalfFill);
    }

    @Test
    public void testFillOuterRectangle() throws Exception {
        String testOuterRectangle =
                "------------\n" +
                "|..........|\n" +
                "|.xxxxxxx..|\n" +
                "|.x     x..|\n" +
                "|.x     x..|\n" +
                "|.x     x..|\n" +
                "|.x     x..|\n" +
                "|.x     x..|\n" +
                "|.xxxxxxx..|\n" +
                "|..........|\n" +
                "|..........|\n" +
                "------------\n";
        commandExecutor.CreateCanvas("C 10 10");
        commandExecutor.drawRectangle("R 2 2 8 8");
        commandExecutor.fill("B 1 1 .");
        assertEquals(commandExecutor.getCanvas().toString(), testOuterRectangle);
    }

    @Test
    public void testFillInnerRectangle() throws Exception {
        String testInnerRectangle =
                "------------\n" +
                "|          |\n" +
                "| xxxxxxx  |\n" +
                "| x.....x  |\n" +
                "| x.....x  |\n" +
                "| x.....x  |\n" +
                "| x.....x  |\n" +
                "| x.....x  |\n" +
                "| xxxxxxx  |\n" +
                "|          |\n" +
                "|          |\n" +
                "------------\n";
        commandExecutor.CreateCanvas("C 10 10");
        commandExecutor.drawRectangle("R 2 2 8 8");
        commandExecutor.fill("B 4 4 .");
        assertEquals(commandExecutor.getCanvas().toString(), testInnerRectangle);
    }

    @Test
    public void testFillRectangle() throws Exception {
        String testFillRectangle =
                "------------\n" +
                "|          |\n" +
                "| lllllll  |\n" +
                "| l     l  |\n" +
                "| l     l  |\n" +
                "| l     l  |\n" +
                "| l     l  |\n" +
                "| l     l  |\n" +
                "| lllllll  |\n" +
                "|          |\n" +
                "|          |\n" +
                "------------\n";
        commandExecutor.CreateCanvas("C 10 10");
        commandExecutor.drawRectangle("R 2 2 8 8");
        commandExecutor.fill("B 2 2 l");
        assertEquals(commandExecutor.getCanvas().toString(), testFillRectangle);
    }

    @Test
    public void testGetCanvas() throws Exception {
        String createCanvasTestString =
                "-----\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "-----\n";
        commandExecutor.CreateCanvas("C 3 3");
        assertEquals(commandExecutor.getCanvas().toString(), createCanvasTestString);
    }

    @Test
    public void testGetCommandValidator() throws Exception {
        assertEquals(commandExecutor.getCommandValidator(), commandValidator);
    }
}