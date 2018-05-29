package pooGame;

import processing.core.PApplet;

import java.util.Random;

public class PooGame extends PApplet {

    private Enemy[] enemy;
    private User user;
    private long startTime;
    private static long endTime;
    private int scoreTime;
    private Random rand;
    private boolean state;

    public PooGame() {
        user = new User();
        startTime = System.currentTimeMillis();
        endTime = 0;
        scoreTime = 0;
        enemy = new Enemy[5];

        for (int i = 0; i < 5; i++)
            enemy[i] = new Enemy();

        rand = new Random();
        state = true;
    }

    public static void main(String[] args) {
        System.out.println("Start");
        new PooGame().runSketch();
    }

    @Override
    public void settings() {
        size(Constants.windowWidth, Constants.windowHeight);
    }

    @Override
    public void setup() {
        background(125);
    }

    public void calculateScore() {
        endTime = System.currentTimeMillis();
        scoreTime = (int) ((endTime - startTime) / 1000.0);
        textSize(20);
        fill(255);
        text("Score: " + scoreTime, 10, 20);
    }

    public void startGame() {
        for (Enemy e : enemy) {
            if (e.getState()) {
                e.setAccel(0, 2);
                e.update();
                e.slow();
                e.limit();
                testCollision(e);
            } else {
                if (rand.nextInt(Constants.timeInterval) == 0)
                    e.setState(true);
            }
        }
    }

    public void testCollision(Enemy e) {
        int userX = user.getX();
        int userY = user.getY();
        int userWidth = user.getWidth();
        int enemyX = e.getX();
        int enemyY = e.getY();
        int enemyWidth = e.getWidth();

        if (userX < enemyX + enemyWidth && userX + userWidth > enemyX && userY <= enemyY) {
            user.changeEnergy();
            e.setState(false);
            e.setX(rand.nextInt(Constants.windowWidth - Constants.enemyWidth));
            e.setY(-Constants.userHeight);

            if (user.getEnergy() == 0)
                overGame();
        }
    }

    public void overGame() {
        fill(255, 0, 0);
        textSize(50);
        text("GameOver", 200, 300);
        fill(0);
        textSize(20);
        text("If you want to restart, Press the UP key", 150, 350);
        state = false;
    }

    public void restartGame() {
        if (state)
            return;

        state = true;

        for (Enemy e : enemy) {
            e.setX(rand.nextInt(Constants.windowWidth - Constants.enemyWidth));
            e.setY(-Constants.userHeight);
        }

        user.setEnergy(Constants.userEnergy);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void keyPressed() {
        if (!state && keyCode == UP)
            restartGame();
    }

    @Override
    public void draw() {
        if (!state)
            return;

        background(125);
        user.draw(this);

        for (Enemy e : enemy) {
            fill(97, 26, 9);
            e.draw(this);
        }

        startGame();
        calculateScore();
        textSize(20);
        fill(255);
        text("Life: " + user.getEnergy(), 10, 50);

        if (keyPressed) {
            if (keyCode == RIGHT) {
                user.setAccel(2, 0);
                user.update();
                user.slow();
                user.limit();
            } else if (keyCode == LEFT) {
                user.setAccel(-2, 0);
                user.update();
                user.slow();
                user.limit();
            }

        }
    }
}
