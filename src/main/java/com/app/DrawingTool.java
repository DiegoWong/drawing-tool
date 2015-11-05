package com.app;

import com.app.model.Canvas;
import com.app.services.CanvasDrawer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DrawingTool {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DrawingToolConfiguration.class);
        CanvasDrawer canvasDrawer = applicationContext.getBean(CanvasDrawer.class);
        canvasDrawer.drawCanvas(new Canvas(2, 2));

    }
}
