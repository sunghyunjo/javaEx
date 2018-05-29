
class Objects {
    private float xPos;
    private float yPos;
    private float speedX;
    private float speedY;
    private float accelX;
    private float accelY;

    Objects(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        accelX = 1;
        accelY = 1;
    }

    void update() {
        if (speedX < 2 && speedX > 0)
            speedX = 2;
        else if (speedX > -2 && speedX < 0)
            speedX = -2;

        if (speedY < 2 && speedY > 0)
            speedY = 2;
        else if (speedY > -2 && speedY < 0)
            speedY = -2;

        xPos += accelX * speedX;
        yPos += accelY * speedY;
    }

    void setSpeed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    void slow() {
        accelX *= 0.5;
        accelY *= 0.5;
    }

    void fast() {
        accelX *= 2;
        accelY *= 2;
    }

    float getX() {
        return xPos;
    }

    float getY() {
        return yPos;
    }

    void setX(int xPos) {
        this.xPos = xPos;
    }

    float getSpeedX() {
        return speedX;
    }

    float getSpeedY() {
        return speedY;
    }


}
