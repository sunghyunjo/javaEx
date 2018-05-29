class Bar extends Objects {

    Bar(int xPos, int yPos) {
        super(xPos, yPos);
    }

    void draw(Game game) {
        game.fill(125, 0, 125);
        move(game);
        limit();
        game.rect(getX(), getY(), Constant.barWidth, Constant.barHeight);
    }

    private void limit() {
        if (getX() >= Constant.windowWidth - Constant.barWidth)
            setX(Constant.windowWidth - Constant.barWidth);
        else if (getX() <= 0)
            setX(0);
    }

    private void move(Game game) {
        if (game.rightKeyPressed())
            setSpeed(Constant.barSpeedX, Constant.barSpeedY);
        else if (game.leftKeyPressed())
            setSpeed(-Constant.barSpeedX, Constant.barSpeedY);

        update();
    }
}
