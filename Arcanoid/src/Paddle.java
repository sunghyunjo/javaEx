import processing.core.PApplet;

class Paddle extends Transform {

    private int paddleWidth;

    Paddle(int paddleWidth, float dirX, float dirY) {
        super(Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT - Constants.BALL_RADIUS, 1, dirX, dirY);
        this.paddleWidth = paddleWidth;
    }

    void draw(PApplet p) {
        limit();
        p.stroke(100, 40, 3);
        p.strokeWeight(20);
        p.line(getPosX() - (paddleWidth / 2), getPosY(), getPosX() + (paddleWidth / 2), getPosY());
    }

    private void limit() {
        if (getPosX() < paddleWidth / 2)
            setPosX(paddleWidth / 2);
        else if (getPosX() > Constants.WINDOW_WIDTH - paddleWidth / 2)
            setPosX(Constants.WINDOW_WIDTH - paddleWidth / 2);
    }

    int getPaddleWidth() {
        return paddleWidth;
    }

    @Override
    public void update() {
        setSpeed(getSpeed() + getDirX());
        setPosX(getPosX() + getSpeed());
        stop();
    }

    private void stop() {
        setSpeed(getSpeed() * 0.9f);
    }
}
