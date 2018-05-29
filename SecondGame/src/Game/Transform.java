package Game;

import processing.core.PApplet;

abstract class Transform {
    private int x;
    private int y;
    private int width;
    private int height;
    private int accelX;
    private int accelY;
    private int speedX;
    private int speedY;

    Transform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(PApplet p);

    void update() {
        speedX += accelX;
        speedY += accelY;
        x += speedX;
        y += speedY;
    }

    void setAccel(int x, int y) {
        accelX = x;
        accelY = y;
    }

    void slow() {
        speedX *= 0.9f;
        speedY *= 0.9f;
    }

    void limit() {
        if (x >= (Constants.windowWidth - Constants.slideBlockWidth))
            x = (Constants.windowWidth - Constants.slideBlockWidth);
        if (x < 0)
            x = 0;
    }

    void boundOnWall(int dirX, int dirY) {
       // setAccel(dirX, dirY);
        if (this.x >= (Constants.windowWidth - Constants.ballWidth / 2))
            setAccel(-dirX, dirY);
        if (this.x < 0)
            setAccel(dirX, -dirY);
        if (this.y < 0)
            setAccel(-dirX, -dirY);
        if (this.y > (Constants.windowHeight - Constants.ballHeight/2))
            setAccel(dirX, dirY);
        update();
        slow();
//        if (super.getX() >= (Constant.WINDOW_WIDTH - Constant.BALL_WIDTH)
//                || super.getX() <= Constant.BALL_WIDTH)
//            super.setSpeed(-super.getSpeedX(), super.getSpeedY());
//
//        else if (super.getY() <= Constant.BALL_HEIGHT)
//            super.setSpeed(super.getSpeedX(), -super.getSpeedY());
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
//
//    void setX(int x) {
//        this.x = x;
//    }
//
//    void setY(int y) {
//        this.y = y;
//    }

}

