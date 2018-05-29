import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Game extends PApplet {
    private Bar bar;
    private MapReader map;
    private List<Ball> balls;
    private List<Brick> bricks;
    private boolean pressRight;
    private boolean pressLeft;
    private long startTime;
    private static long endTime;
    private int scoreTime;

    private Game() {
        bar = null;
        map = null;
        balls = null;
        bricks = null;
        pressRight = false;
        pressLeft = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        scoreTime = 0;
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void settings() {
        size(Constant.windowWidth, Constant.windowHeight);
    }

    @Override
    public void setup() {
        background(0);
        smooth();
    }

    @Override
    public void draw() {
        background(0);
        calculateScore();

        if (bar == null && balls == null)
            gameStart();

        else {
            if (bar != null)
                bar.draw(this);

            map.read();

            for (int i = 0; i < bricks.size(); i++) {
                bricks.get(i).draw(this);

                if (bricks.get(i).getItem() == -1)
                    bricks.remove(i);
            }
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).ballState()) {
                    balls.remove(i);
                    if (balls.size() == 0) {
                        gameOver();
                        break;
                    }
                } else
                    balls.get(i).draw(this, bar, bricks, balls);
            }

        }
    }

    @Override
    public void keyPressed() {
        if (keyCode == RIGHT && bar != null)
            pressRight = true;
        else if (keyCode == LEFT && bar != null)
            pressLeft = true;
    }

    @Override
    public void keyReleased() {
        if (keyCode == RIGHT && bar != null) {
            pressRight = false;
            bar.setSpeed(0, 0);
        } else if (keyCode == LEFT && bar != null) {
            pressLeft = false;
            bar.setSpeed(0, 0);
        }
    }

    private void calculateScore() {
        endTime = System.currentTimeMillis();
        scoreTime = (int) ((endTime - startTime) / 1000.0);
        textSize(20);
        fill(255);
        text("Score: " + scoreTime, Constant.windowWidth/2,
                Constant.windowHeight/2);
    }

    boolean rightKeyPressed() {
        return pressRight;
    }

    boolean leftKeyPressed() {
        return pressLeft;
    }

    private void gameStart() {
        bricks = new ArrayList<>();
        map = new MapReader(bricks);
        map.readFile();

        for (int i = 0; i < map.getFileLength(); i++)
            bricks.add(new Brick());

        bar = new Bar(Constant.initBarX, Constant.initBarY);
        balls = new ArrayList<>();
        balls.add(new Ball(Constant.initBallX, Constant.initBallY));
    }

    private void gameOver() {
        bar = null;
        balls = null;
        pressRight = false;
        pressLeft = false;
        bricks = null;
        map = null;
    }
}
