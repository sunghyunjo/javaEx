import processing.core.PApplet;

public class Circle extends Shape {

    public Circle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet p) {
        super.draw(p);
        p.ellipse(getX(), getY(), getWidth(), getHeight());
    }

    public int radiusX() {
        return getWidth() / 2;
    }

    public int radiusY() {
        return getHeight() / 2;
    }

    @Override
    public boolean testCollision(int mouseX, int mouseY) {
        int x = getX();
        int y = getY();
        int radiusX = radiusX();
        int radiusY = radiusY();

        return mouseX > (x - radiusX) && mouseX < (x + radiusX)
                && mouseY < (y + radiusY) && mouseY > (y - radiusY);
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }
}
