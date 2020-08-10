package com.bogdan.Shapes;

import com.bogdan.Logic.Base;
import com.bogdan.Logic.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Ball extends Base {

    public Ball(GraphicsContext gc, List<Shape> shapes, double x, double y) {
        super(gc, shapes, x, y);
    }

    public Ball(Base shape) {
        super(shape);
    }

    public Ball() {
    }

    @Override
    public void draw() {
        gc.strokeOval(x, y, SHAPE_SIZE, SHAPE_SIZE);
    }

    public void enterDraw(){
        gc.setFill(Color.RED);
        gc.fillOval(x, y, SHAPE_SIZE, SHAPE_SIZE);
    }
}
