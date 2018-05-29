package pooGame;

import processing.core.PApplet;

public abstract class Transform {
    private int x;
    private int y;
    private int width;
    private int height;
    private int accelX;
    private int accelY;
    private int speedX;
    private int speedY;
    private boolean state;

    public Transform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        state = false;
    }

    public abstract void draw(PApplet p);

    public void update() {
        speedX += accelX;
        speedY += accelY;
        x += speedX;
        y += speedY;
    }

    public void setAccel(int x, int y) {
        accelX = x;
        accelY = y;
    }

    public void slow() {
        speedX *= 0.9f;
        speedY *= 0.9f;
    }

    public void limit() {
        if (x > (Constants.windowWidth - Constants.userWidth))
            x = (Constants.windowWidth - Constants.userWidth);
        if (x < 0)
            x = 0;
        if (y > Constants.windowHeight)
            initialEnemy();
    }

    public void initialEnemy() {
        state = false;
        y = -Constants.userHeight;
    }

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
