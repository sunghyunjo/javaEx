import processing.core.PApplet;

import java.util.List;

class Item {
    private float posX;
    private float posY;
    private ItemEffect itemEffect;

    Item(ItemEffect itemType, float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.itemEffect = itemType;
    }

    void update() {
        posY = posY + 5;
    }

    void draw(PApplet p) {
        getItemEffect().fill(p);
        p.noStroke();
        p.ellipse(posX, posY, Constants.BALL_SIZE, Constants.BALL_SIZE);
    }

    boolean checkCollision(Paddle bar, List<Ball> ballList) {
        if (posY > (Constants.WINDOW_HEIGHT - Constants.BALL_RADIUS) && posY <= (Constants.WINDOW_HEIGHT - Constants.BALL_RADIUS + Constants.PADDLE_HEIGHT)
                && posX < bar.getPosX() + (bar.getPaddleWidth() / 2) && posX >= bar.getPosX() - (bar.getPaddleWidth() / 2)) {
            checkItem(ballList);
            return true;
        }
        return false;
    }

    boolean removeItem() {
        return posY > Constants.WINDOW_HEIGHT - Constants.BALL_RADIUS * 2;
    }

    private void checkItem(List<Ball> ballList) {
        for (Ball balls : ballList) {
            getItemEffect().run(balls);
        }
    }

    private ItemEffect getItemEffect() {
        return itemEffect;
    }

}