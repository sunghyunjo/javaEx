class Transform {

    private Vector2 dir;
    private Vector2 pos;
    private float velocity;

    Transform(float posX, float posY, float speed, float dirX, float dirY) {
        dir = new Vector2(dirX, dirY);
        dir.normalize();
        pos = new Vector2(posX, posY);
        velocity = speed;
    }

    public void update() {
        pos = Vector2.add(pos, Vector2.mul(dir, velocity));
        if (pos.x < 0)
            dir.x = -dir.x;
        else if (pos.x > Constants.WINDOW_WIDTH)
            dir.x = -dir.x;

        if (pos.y < 0)
            dir.y = -dir.y;

        velocity += 0.1f;
        if (velocity > 3) velocity = 3;
    }

    void changeSpeed(float speed) {
        this.velocity = speed;
    }

    float getDirX() {
        return dir.x;
    }

    float getDirY() {
        return dir.y;
    }

    Vector2 getDir() {
        return dir;
    }

    float getPosX() {
        return pos.x;
    }

    float getPosY() {
        return pos.y;
    }

    float getSpeed() {
        return velocity;
    }

    void setPosX(float posX) {
        this.pos.x = posX;
    }

    void setPosY(float posY) {
        this.pos.y = posY;
    }

    void setDir(Vector2 direction) {
        dir = direction;
    }

    void setSpeed(float speed) {
        this.velocity = speed;
    }

}
