package pooGame;

import processing.core.PApplet;

import java.util.Random;

public class Enemy extends Transform {

    static Random rand = new Random();

    public Enemy() {
        super((rand.nextInt(Constants.windowWidth - Constants.enemyWidth)), Constants.enemyY, Constants.enemyWidth, Constants.enemyHeight);
    }

    @Override
    public void draw(PApplet p) {
        p.rect(getX(), getY(), getWidth(), getHeight());
    }
}
