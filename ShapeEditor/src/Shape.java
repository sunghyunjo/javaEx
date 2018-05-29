import processing.core.PApplet;

abstract class Shape implements Cloneable {
    private int x;
    private int y;
    private int width;
    private int height;
    private int strokeWeight;
    private Color color;

    public Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = new Color(255, 255, 255);
    }

    public void draw(PApplet p) {
        p.strokeWeight(getStrokeWeight());
        p.fill(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Shape clone() {
        try {
            Shape shape = (Shape) super.clone();
            shape.setX(shape.getX() + shape.getWidth() + 10);
            shape.color = this.color.clone();
            return shape;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void controlStroke(int wheelValue) {
        if (wheelValue < 0)
            this.strokeWeight++;
        else
            this.strokeWeight--;

        if (this.strokeWeight < 0)
            this.strokeWeight = 0;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    protected abstract boolean testCollision(int mouseX, int mouseY);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStrokeWeight() {
        return strokeWeight;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public Color setGray(int wheelValue) {
        color.controlGray(wheelValue);
        return color;
    }

    public Color setRGB(int wheelValue, char colorCode) {
        color.controlRGB(wheelValue, colorCode);
        return color;
    }
}
