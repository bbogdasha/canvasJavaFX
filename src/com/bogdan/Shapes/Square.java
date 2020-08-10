package com.bogdan.Shapes;

import com.bogdan.Logic.Base;
import com.bogdan.Logic.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Square extends Base {

    public Square(GraphicsContext gc, List<Shape> shapes, double x, double y) {
        super(gc, shapes, x, y);
    }

    public Square(Base shape) {
        super(shape);
    }

    public Square() {
    }

    @Override
    public void draw() {
        gc.strokeRoundRect(x, y, SHAPE_SIZE, SHAPE_SIZE, 0, 0);
    }

    public void enterDraw(){
        gc.setFill(Color.PINK);
        gc.fillRect(x, y, SHAPE_SIZE, SHAPE_SIZE);
    }
}