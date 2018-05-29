class Brick {
    private int brickWidth;
    private int brickHeight;
    private ItemEffect itemType;
    private float posX;
    private float posY;

    Brick(float posX, float posY, ItemEffect itemType) {
        this.brickWidth = Constants.BRICK_WIDTH;
        this.brickHeight = Constants.BRICK_HEIGHT;
        this.posX = posX;
        this.posY = posY;
        this.itemType = itemType;
    }

    ItemEffect getItemType() {
        return itemType;
    }

    int getBrickWidth() {
        return brickWidth;
    }

    int getBrickHeight() {
        return brickHeight;
    }

    float getPosX() {
        return posX;
    }

    float getPosY() {
        return posY;
    }
}
