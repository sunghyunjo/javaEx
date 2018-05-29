import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProcessingEntry extends PApplet {

    private List<Shape> shapes;
    private boolean controlState;
    private boolean dState;

    public ProcessingEntry() {
        shapes = new ArrayList<>();
        controlState = false;
        dState = false;
    }


    public static void main(String[] args) {
        System.out.println("Start");
        new ProcessingEntry().runSketch();
    }

    public void settings() {
        size(800, 600);

    }

    public void setup() {
        background(125);
    }

    public Shape outputCollisions(int x, int y) {
        for (Shape shape : shapes) {
            if (!shape.testCollision(x, y))
                continue;
            return shape;
        }
        return null;
    }

    @Override
    public void mouseClicked() {
        if (mouseButton != LEFT)
            return;
        makeShape();
    }

    @Override
    public void keyPressed() {
        if (key == DELETE) {
            removeShape();
        } else if (keyCode == CONTROL)
            controlState = true;
        else if (keyCode == 'D' || keyCode == 'd') {
            dState = true;
        }

        if (controlState && dState) {
            cloneShape();
        }
    }

    @Override
    public void keyReleased() {
        if (keyCode == CONTROL)
            controlState = false;
        else if (keyCode == 'D' || keyCode == 'd')
            dState = false;
    }

    public void removeShape() {
        Shape shape = outputCollisions(mouseX, mouseY);
        shapes.remove(shape);
    }

    @Override
    public void mouseWheel(processing.event.MouseEvent e) {
        int keyCode = Character.toUpperCase(key);
        Shape shape = outputCollisions(mouseX, mouseY);

        int value = e.getCount();
        if (keyCode == 'C')
            shape.controlGray(value);
        else if (keyCode == 'S')
            shape.controlStroke(value);
        else if (keyCode == 'R')
            shape.controlRed(value, 'r');
        else if (keyCode == 'G')
            shape.controlRed(value, 'g');
        else if (keyCode == 'B')
            shape.controlRed(value, 'b');
    }

    public void cloneShape() {
        Shape shape = outputCollisions(mouseX, mouseY);
        Shape copyShape;
        if (shape == null)
            return;
        copyShape = shape.clone();
        shapes.add(copyShape);

    }

    private void makeShape() {
        if (key == '1')
            shapes.add(new Rect(mouseX, mouseY, 100, 100));
        else if (key == '2')
            shapes.add(new Circle(mouseX, mouseY, 100, 100));
    }

    @Override
    public void draw() {
        background(125);
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }
}
