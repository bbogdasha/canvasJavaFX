package com.bogdan;

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
                selectNextShape();
                break;
            case DIGIT2:
                Shape square = new Square(gc, shapes, 70, 180);
                shapes.add(square);
                selectNextShape();
                break;
            case DIGIT3:
                Shape triangle = new Triangle(gc, shapes, 180, 70);
                shapes.add(triangle);
                selectNextShape();
                break;
            case DIGIT4:
                Shape pacman = new Pacman(gc, shapes, 240, 240);
                shapes.add(pacman);
                selectNextShape();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

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
