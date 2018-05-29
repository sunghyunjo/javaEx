import processing.core.PApplet;

import java.util.List;

class Ball extends Objects {

    Ball(float xPos, float yPos) {
        super(xPos, yPos);
        super.setSpeed(Constant.ballSpeedX, Constant.ballSpeedY);
    }

    void draw(PApplet p, Bar bar, List<Brick> bricks, List<Ball> balls) {
        p.fill(255, 255, 0);
        update(bar, bricks, balls);
        p.ellipse(getX(), getY(), Constant.ballWidth, Constant.ballHeight);
    }

    private void update(Bar bar, List<Brick> bricks, List<Ball> balls) {
        super.update();
        collisionWithWall();
        collisionWithBar(bar);
        collisionWithBrick(bricks, balls);
    }

    private void collisionWithWall() {
        if (getX() >= (Constant.windowWidth - Constant.ballWidth) || getX() <= Constant.ballWidth)
            setSpeed(-getSpeedX(), getSpeedY());
        else if (getY() <= Constant.ballHeight)
            setSpeed(getSpeedX(), -getSpeedY());
    }

    private void collisionWithBar(Bar bar) {
        float barLeftX = bar.getX() - Constant.ballWidth / 2;
        float barMiddleX = (bar.getX() + Constant.barWidth / 2);
        float barRightX = (bar.getX() + Constant.ballWidth / 2 + Constant.barWidth);
        float barUpside = (bar.getY() - Constant.ballHeight / 2);

        if ((getX() >= barLeftX && getX() <= barMiddleX) && getY() >= barUpside) {
            if (getSpeedX() > 0)
                setSpeed(-getSpeedX(), -getSpeedY());
            else
                setSpeed(getSpeedX(), -getSpeedY());
        } else if ((getX() > barMiddleX && getX() <= barRightX) && getY() >= barUpside) {
            if (getSpeedX() > 0)
                setSpeed(getSpeedX(), -getSpeedY());
            else
                setSpeed(-getSpeedX(), -getSpeedY());
        }
    }

    boolean ballState() {
        return getY() >= (Constant.windowHeight - Constant.ballHeight);
    }

    private void collisionWithBrick(List<Brick> brickList, List<Ball> ballList) {

        for (int i = 0; i < brickList.size(); i++) {
            int brickLeftX = brickList.get(i).getX() - Constant.ballWidth / 2;
            int brickMiddleX = (brickList.get(i).getX() + Constant.brickWidth / 2);
            int brickRightX = (brickList.get(i).getX() + Constant.ballWidth / 2 + Constant.brickWidth);
            int brickUpside = (brickList.get(i).getY() - Constant.ballHeight / 2);
            int brickDownSide = (brickList.get(i).getY() + Constant.ballHeight / 2 + Constant.brickHeight);

            if ((getX() >= brickLeftX && getX() <= brickMiddleX)
                    && (getY() >= brickUpside) && (getY() <= brickDownSide)) {

                if (brickList.get(i).getItem() == 1) {
                    ballList.add(new Ball(getX(), getY()));
                } else if (brickList.get(i).getItem() == 2) {
                    slow();
                } else if (brickList.get(i).getItem() == 3) {
                    fast();
                } else if (brickList.get(i).getItem() == 4) {
                    brickList.remove(i);
                    continue;
                }

                if (getSpeedX() > 0)
                    setSpeed(-getSpeedX(), -getSpeedY());
                else
                    setSpeed(getSpeedX(), -getSpeedY());

                brickList.remove(i);
            } else if ((getX() > brickMiddleX && getX() <= brickRightX)
                    && (getY() >= brickUpside && getY() <= brickDownSide)) {

                if (brickList.get(i).getItem() == 1) {
                    ballList.add(new Ball(getX(), getY()));
                } else if (brickList.get(i).getItem() == 2) {
                    slow();
                } else if (brickList.get(i).getItem() == 3) {
                    fast();
                } else if (brickList.get(i).getItem() == 4) {
                    brickList.remove(i);
                    continue;
                }

                if (getSpeedX() > 0)
                    setSpeed(getSpeedX(), -getSpeedY());
                else
                    setSpeed(-getSpeedX(), -getSpeedY());

                brickList.remove(i);
            }

        }
    }
}
