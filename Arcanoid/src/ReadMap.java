import processing.core.PApplet;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class ReadMap {
    private List<Brick> brickList;
    private List<Item> itemList;
    private int stage;

    ReadMap() {
        brickList = new ArrayList<>();
        itemList = new ArrayList<>();
        stage = 1;
    }

    void createMap() {
        String mapfile;

        if (stage == 1)
            mapfile = "./map.txt";
        else
            mapfile = "./map.txt";

        try {
            FileReader reader = new FileReader(mapfile);
            int value;
            int brickX = 0;
            int brickY = 0;

            while ((value = reader.read()) != -1) {
                if (value == 10) {
                    brickY += Constants.BRICK_HEIGHT;
                    brickX = 0;
                } else if (value == 48) {
                    brickList.add(new Brick(brickX, brickY, ItemEffect.NORMAL));
                    brickX += Constants.BRICK_WIDTH;
                } else if (value == 49) {
                    brickList.add(new Brick(brickX, brickY, ItemEffect.FAST));
                    brickX += Constants.BRICK_WIDTH;
                } else if (value == 50) {
                    brickList.add(new Brick(brickX, brickY, ItemEffect.SLOW));
                    brickX += Constants.BRICK_WIDTH;
                } else if (value == 51) {
                    brickList.add(new Brick(brickX, brickY, ItemEffect.POWERUP));
                    brickX += Constants.BRICK_WIDTH;
                } else if (value == 52) {
                    brickList.add(new Brick(brickX, brickY, ItemEffect.ADDBALL));
                    brickX += Constants.BRICK_WIDTH;
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    void drawMap(PApplet p, Paddle bar, List<Ball> ballList) {
        p.fill(255, 255, 0);
        p.stroke(0);
        p.strokeWeight(1);
        for (Brick b : brickList)
            p.rect(b.getPosX(), b.getPosY(), Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
        drawItem(p, bar, ballList);
    }

    void checkCollision(Ball ball) {
        int i = 0;
        while (i < brickList.size()) {
            Brick b = brickList.get(i);
            if ((ball.getPosX() - Constants.BALL_RADIUS <= b.getPosX() + b.getBrickWidth()
                    && ball.getPosX() + Constants.BALL_RADIUS >= b.getPosX())
                    && ball.getPosY() - Constants.BALL_RADIUS <= b.getPosY() + b.getBrickHeight()
                    && ball.getPosY() + Constants.BALL_RADIUS >= b.getPosY()) {
                if (checkCollisionWithBottom(ball, b) || checkCollisionWithTop(ball, b)) {
                    if (!Ball.isPower())
                        ball.setDir(new Vector2(ball.getDirX(), -ball.getDirY()));
                    setAfterCollision(ball, b);
                } else if (checkCollisionWithRight(ball, b) || checkCollisionWithLeft(ball, b)) {
                    if (!Ball.isPower())
                        ball.update();
                    setAfterCollision(ball, b);
                }
            }
            i++;
        }
    }

    private void createItem(ItemEffect itemType, Brick b) {
        Item item = new Item(itemType, b.getPosX() + (Constants.BRICK_WIDTH / 2), b.getPosY() + Constants.BRICK_HEIGHT / 2);
        itemList.add(item);
    }

    private void drawItem(PApplet p, Paddle bar, List<Ball> ballList) {
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).update();
            itemList.get(i).draw(p);
            if (itemList.get(i).checkCollision(bar, ballList)) {
                itemList.remove(i);
            } else if (itemList.get(i).removeItem())
                itemList.remove(i);
        }
    }

    int checkBrick() {
        if (brickList.size() != 0)
            return 0;
        if (stage == 2)
            return 1;
        stage = stage + 1;
        createMap();
        return 2;
    }

    private boolean checkCollisionWithBottom(Ball ball, Brick b) {
        float brickMiddleY = b.getPosY() + (Constants.BRICK_HEIGHT / 2);
        return (ball.getPosY() - Constants.BALL_RADIUS <= b.getPosY() + Constants.BRICK_HEIGHT && ball.getPosY() - Constants.BALL_RADIUS >= brickMiddleY);
    }

    private boolean checkCollisionWithTop(Ball ball, Brick b) {
        float brickMiddleY = b.getPosY() + (Constants.BRICK_HEIGHT / 2);
        return ball.getPosY() + Constants.BALL_RADIUS >= b.getPosY() && ball.getPosY() + Constants.BALL_RADIUS <= brickMiddleY;
    }

    private boolean checkCollisionWithRight(Ball ball, Brick b) {
        float brickMiddleX = b.getPosX() + (Constants.BRICK_WIDTH / 2);
        return ball.getPosX() - Constants.BALL_RADIUS <= b.getPosX() + Constants.BRICK_WIDTH && ball.getPosX() - Constants.BALL_RADIUS >= brickMiddleX;
    }

    private boolean checkCollisionWithLeft(Ball ball, Brick b) {
        float brickMiddleX = b.getPosX() + (Constants.BRICK_WIDTH / 2);
        return ball.getPosX() + Constants.BALL_RADIUS >= b.getPosX() && ball.getPosX() + Constants.BALL_RADIUS <= brickMiddleX;
    }

    private void setAfterCollision(Ball ball, Brick b) {
        ball.setPaddleCollision(false);
        if (b.getItemType() != ItemEffect.NORMAL)
            createItem(b.getItemType(), b);
        brickList.remove(b);
    }
}