package com.bogdan;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Triangle extends Base{

    public Triangle(GraphicsContext gc, List<Shape> shapes, double x, double y) {
        super(gc, shapes, x, y);
    }

    public Triangle(Base shape) {
        super(shape);
    }

    public Triangle() {
    }

    @Override
    public void draw() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokePolygon(new double[]{x, x + SHAPE_SIZE / 2, x + SHAPE_SIZE},
                new double[]{y + SHAPE_SIZE, y, y + SHAPE_SIZE}, 3);
    }

    public void enterDraw(){
        gc.setFill(Color.GREEN);
        gc.fillPolygon(new double[]{x, x + SHAPE_SIZE / 2, x + SHAPE_SIZE},
                new double[]{y + SHAPE_SIZE, y, y + SHAPE_SIZE}, 3);
    }
}