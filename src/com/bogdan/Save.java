package com.bogdan;

import java.util.ArrayList;
import java.util.List;

public class Save {

    private List<Shape> shapes = new ArrayList<>();

    public Save() {
    }

    public Save(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
