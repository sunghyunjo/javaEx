import processing.core.PApplet;

public class Rect extends Shape {

    public Rect(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet p) {
        super.draw(p);
        p.rect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean testCollision(int mouseX, int mouseY) {
        int x = getX();
        int y = getY();

        return mouseX > x - getWidth()/2 && mouseX < x + getWidth()/2 && mouseY > y - getWidth()/2 && y < y + getHeight()/2;
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }
}
