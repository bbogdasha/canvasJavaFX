package com.bogdan.Controller;

import com.bogdan.Logic.Base;
import com.bogdan.Logic.Shape;
import com.bogdan.Shapes.Ball;
import com.bogdan.Shapes.Pacman;
import com.bogdan.Shapes.Square;
import com.bogdan.Shapes.Triangle;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final GraphicsContext gc;
    private List<Shape> shapes = new ArrayList<>();
    private int index = 0;

    public Board(GraphicsContext gc) {
        this.gc = gc;
        Shape ball = new Ball(gc, shapes, 100, 100);
        shapes.add(ball);
        draw();
    }

    public void keyBoard(KeyEvent keyEvent){

        Shape shape = shapes.get(index);

        switch (keyEvent.getCode()){
            case LEFT:
                shape.moveLeft();
                break;
            case RIGHT:
                shape.moveRight();
                break;
            case UP:
                shape.moveUp();
                break;
            case DOWN:
                shape.moveDown();
                break;
            case MINUS:
                shape.sizeMinus();
                break;
            case EQUALS:
                shape.sizePlus();
                break;
            case DIGIT1:
                Shape ball = new Ball(gc, shapes, 100, 100);
                shapes.add(ball);
                selectLostShape();
                break;
            case DIGIT2:
                Shape square = new Square(gc, shapes, 100, 100);
                shapes.add(square);
                selectLostShape();
                break;
            case DIGIT3:
                Shape triangle = new Triangle(gc, shapes, 100, 100);
                shapes.add(triangle);
                selectLostShape();
                break;
            case DIGIT4:
                Shape pacman = new Pacman(gc, shapes, 100, 100);
                shapes.add(pacman);
                selectLostShape();
                break;
            case TAB:
                selectNextShape();
                break;
            case S:
                save();
                break;
            case L:
                load();
                break;
            case DELETE:
                remove();
                break;
        }
        draw();
    }

    public void save() {
        Save save = new Save(shapes);

        JSONSerializer serializer = new JSONSerializer().prettyPrint(true);
        String json = serializer.deepSerialize(save);

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("Save.txt"))) {
            buffer.write(json);
        } catch (IOException e) {
            System.out.println("Could not open file!");
        }
    }

    public void load() {
        try {
            String file = new String(Files.readAllBytes(Paths.get("Save.txt")));

            JSONDeserializer<Save> deserializer = new JSONDeserializer<>();
            Save saveDeserializer = deserializer.deserialize(file);
            List<Shape> shapesList = saveDeserializer.getShapes();

            for (Shape shape : shapesList) {
                ((Base) shape).setGc(gc);
            }
            shapes = shapesList;
        } catch (IOException e) {
            System.out.println("Could not open file!");
        }
    }

    private void selectLostShape() {
        index = shapes.size() - 1;
    }

    private void selectNextShape() {
        index++;
        if (index >= shapes.size()) {
            index = 0;
        }
    }

    private void remove(){
        if (shapes.size() > 1){
            shapes.remove(index);
            System.out.println(index);
            if (index != 0){
                index--;
            }
        }
    }

    private void clean() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight(), 0, 0);
    }

    private void draw() {
        Shape shape = shapes.get(index);
        clean();
        shape.enterDraw();
        shape.draw();
        for (Shape shapes : shapes) {
            shapes.draw();
        }
    }
}
