package com.bogdan.Shapes;

import com.bogdan.Logic.Base;
import com.bogdan.Logic.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.List;

public class Pacman extends Base {

    public Pacman(GraphicsContext gc, List<Shape> shapes, double x, double y) {
        super(gc, shapes, x, y);
    }

    public Pacman(Base shape) {
        super(shape);
    }

    public Pacman() {
    }

    @Override
    public void draw() {
        gc.strokeArc(x, y, SHAPE_SIZE, SHAPE_SIZE, 45, 240, ArcType.ROUND);
    }

    public void enterDraw(){
        gc.setFill(Color.YELLOW);
        gc.fillArc(x, y, SHAPE_SIZE, SHAPE_SIZE, 45, 240, ArcType.ROUND);
    }
}