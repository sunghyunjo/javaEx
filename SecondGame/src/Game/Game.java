package Game;

import processing.core.PApplet;

public class Game extends PApplet {

    private Block block;
    private SlideBlock slideBlock;
    private Ball ball;

    private Game() {
        block = new Block();
        ball = new Ball();
        slideBlock = new SlideBlock();
    }

    public static void main(String[] args) {
        System.out.println("Start");
        new Game().runSketch();
    }

    private void moveSlideBlock(int x, int y) {
        slideBlock.setAccel(x, y);
        slideBlock.update();
        slideBlock.slow();
        slideBlock.limit();
    }

    private void bounceBall(int x, int y) {
        ball.setAccel(x, y);
        ball.update();
        ball.slow();
        ball.boundOnWall(x, y);
    }

    @Override
    public void settings() {
        size(Constants.windowWidth, Constants.windowHeight);
        smooth();
    }

    @Override
    public void setup() {
        background(Constants.backgroundColor);

    }

    @Override
    public void draw() {
        background(Constants.backgroundColor);
        slideBlock.draw(this);
        ball.draw(this);
        bounceBall(1, -1);
        block.readTextFile(this);
        block.draw(this);

        if (keyPressed) {
            if (keyCode == RIGHT)
                moveSlideBlock(2, 0);
            else if (keyCode == LEFT)
                moveSlideBlock(-2, 0);
        }
    }
}
