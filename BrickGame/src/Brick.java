import processing.core.PApplet;

class Brick {
    private int xPos;
    private int yPos;
    boolean drawCheck;
    int item;

    Brick() {
        drawCheck = false;
    }

    void draw(PApplet p) {
        if (drawCheck) {
            p.stroke(53, 12, 1);
            p.fill(Constant.brickRedColor, 45, 0);
            p.rect(xPos, yPos, Constant.brickWidth, Constant.brickHeight);
        }
    }

    int getX() {
        return xPos;
    }

    int getY() {
        return yPos;
    }

    void setX(int xPos) {
        this.xPos = xPos;
    }

    void setY(int yPos) {
        this.yPos = yPos;
    }

    int getItem() {
        return item;
    }
}
