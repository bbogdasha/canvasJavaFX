package com.bogdan;

import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public abstract class Base implements Shape{

    protected double x;
    protected double y;
    protected double SHAPE_SIZE = 50;
    protected transient List<Shape> shapes;
    protected transient GraphicsContext gc;

    public Base(GraphicsContext gc, List<Shape> shapes, double x, double y) {
        this.gc = gc;
        this.shapes = shapes;
        this.x = x;
        this.y = y;

        double SCREEN_X = gc.getCanvas().getWidth();
        double SCREEN_Y = gc.getCanvas().getHeight();
    }

    public Base() {
    }

    public Base(Base shape) {
        this(shape.gc, shape.shapes, shape.x, shape.y);
        this.SHAPE_SIZE = shape.SHAPE_SIZE;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSHAPE_SIZE() {
        return SHAPE_SIZE;
    }

    public void setSHAPE_SIZE(double SHAPE_SIZE) {
        this.SHAPE_SIZE = SHAPE_SIZE;
    }


    @Override
    public void moveRight() {
        if (x + SHAPE_SIZE < gc.getCanvas().getWidth()){
            x += 2;
        }
    }

    @Override
    public void moveLeft() {
        if (x > 0){
            x -= 2;
        }
    }

    @Override
    public void moveDown() {
        if (y + SHAPE_SIZE < gc.getCanvas().getHeight()) {
            y += 2;
        }
    }

    @Override
    public void moveUp() {
        if (y > 0){
            y -= 2;
        }
    }

    @Override
    public void sizePlus() {
        if (SHAPE_SIZE != 700){
            SHAPE_SIZE += 2;
        }
    }

    @Override
    public void sizeMinus() {
        if (SHAPE_SIZE != 10){
            SHAPE_SIZE -= 2;
        }
    }
}
