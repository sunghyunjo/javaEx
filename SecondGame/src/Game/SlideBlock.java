package Game;

import processing.core.PApplet;

class SlideBlock extends Transform {

    SlideBlock() {
        super(Constants.slideBlockX, Constants.slideBlockY, Constants.slideBlockWidth, Constants.slideBlockkHeight);
    }

    @Override
    public void draw(PApplet p) {
        p.fill(Constants.slideBlockColor);
        p.stroke(Constants.slideBlockColor);
        p.rect(getX(), getY(), getWidth(), getHeight());
    }

}
