package Game;

import processing.core.PApplet;

class Ball extends Transform{

    Ball(){
        super(Constants.ballX, Constants.ballY, Constants.ballWidth, Constants.ballHeight);
    }

    @Override
    public void draw(PApplet p){
        p.fill(Constants.ballColor);
        p.stroke(Constants.ballColor);
        p.ellipse(getX(), getY(), getWidth(), getHeight());
    }

}
