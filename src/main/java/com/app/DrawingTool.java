package com.app;

import com.app.model.Canvas;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DrawingTool {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DrawingToolConfiguration.class);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Canvas canvas = new Canvas(2, 2);

    }
}
