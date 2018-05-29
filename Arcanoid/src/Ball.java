import processing.core.PApplet;

class Ball extends Transform {

    private boolean paddleCollision;
    private static boolean power;

    Ball(float posX, float posY, float dirX, float dirY) {
        super(posX, posY, Constants.BALL_SPEED, dirX, dirY);
        power = false;
        paddleCollision = false;
        setSpeed((float) Math.sqrt(Math.pow(this.getSpeed(), 2) + Math.pow(this.getSpeed(), 2)));
    }

    void draw(PApplet p) {
        p.fill(255);
        p.noStroke();
        p.ellipse(this.getPosX(), this.getPosY(), Constants.BALL_SIZE, Constants.BALL_SIZE);
    }

    void CheckCollisionWithPaddle(Paddle paddle) {
        float distance;

        if (paddleCollision)
            return;
        if (getPosY() <= Constants.WINDOW_HEIGHT - Constants.PADDLE_HEIGHT - Constants.BALL_RADIUS)
            return;

        if (getPosX() < paddle.getPosX() + (paddle.getPaddleWidth() / 2) + Constants.BALL_RADIUS
                && this.getPosX() > paddle.getPosX() - (paddle.getPaddleWidth() / 2) - Constants.BALL_RADIUS) {
            distance = (getPosX() - paddle.getPosX()) / (Constants.PADDLE_WIDTH / 2);
            setDir(new Vector2(distance, -getDirY()));
            getDir().normalize();
            update();
            paddleCollision = true;
        }
    }

    int checkCollisionWithWall() {
        update();
        if (getPosY() > Constants.WINDOW_HEIGHT + Constants.BALL_RADIUS)
            return 3;
        else
            paddleCollision = false;
        return 0;
    }

    void ballInit(float posX) {
        update();
        setSpeed(Constants.BALL_SPEED);
        setPosX(posX);
        setPosY(Constants.WINDOW_HEIGHT - Constants.BALL_SIZE);
        power = false;
        paddleCollision = false;
    }

    void setPaddleCollision(boolean paddleCollision) {
        this.paddleCollision = paddleCollision;
    }

    static void setPower(boolean power) {
        Ball.power = power;
    }

    static boolean isPower() {
        return power;
    }

}
