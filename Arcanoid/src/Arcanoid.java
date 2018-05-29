import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Arcanoid extends PApplet {
    private Paddle paddle;
    private Ball ball;
    private ReadMap readMap;
    private int gameState;
    private static List<Ball> ballList;

    private Arcanoid() {
        paddle = new Paddle(Constants.PADDLE_WIDTH, 1, 0);
        ball = new Ball(paddle.getPosX() - Constants.BALL_RADIUS, Constants.WINDOW_HEIGHT - Constants.PADDLE_HEIGHT - Constants.BALL_RADIUS, 1, 1);
        ballList = new ArrayList<>();
        gameState = 2;
        readMap = new ReadMap();
    }

    public static void main(String args[]) {
        Arcanoid arcanoid = new Arcanoid();
        arcanoid.runSketch();
    }

    @Override
    public void settings() {
        size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        readMap.createMap();
        ballList.add(ball);
    }

    @Override
    public void setup() {
        background(0);
    }

    @Override
    public void draw() {
        background(0);
        setGameState();
        if (gameState != 0)
            return;

        readMap.drawMap(this, paddle, ballList);
        for (Ball ball : ballList)
            readMap.checkCollision(ball);

        if (keyPressed) {
            if (keyCode == RIGHT)
                paddle.setDir(new Vector2(1, 0));
            else if (keyCode == LEFT)
                paddle.setDir(new Vector2(-1, 0));
        }

        paddle.update();
        paddle.draw(this);
        checkCollision();
    }

    @Override
    public void keyPressed() {
        if (keyCode == ENTER)
            gameState = 0;
    }

    @Override
    public void keyReleased() {
        paddle.setDir(new Vector2(0, 0));
    }

    private void overGame() {
        fill(255, 0, 255);
        textSize(50);
        text("The End", Constants.WINDOW_WIDTH / 2 - 50, Constants.WINDOW_HEIGHT / 2);
    }

    private void startGame() {
        fill(255);
        textSize(25);
        text("Enter Pressed", 350, 300);
        ballList.get(0).ballInit(paddle.getPosX() - Constants.BALL_RADIUS);
    }

    private void checkCollision() {
        for (int i = 0; i < ballList.size(); i++) {
            ballList.get(i).update();
            ballList.get(i).draw(this);
            ballList.get(i).CheckCollisionWithPaddle(paddle);
            Time.checkItemTime(ballList.size(), ballList);
            gameState = ballList.get(i).checkCollisionWithWall();
            if (gameState != 3)
                return;
            ballList.remove(i);
            gameState = 0;
        }
    }

    private void setGameState() {
        if (ballList.size() == 0)
            gameState = 1;
        if (gameState == 0)
            gameState = readMap.checkBrick();
        else {
            if (gameState == 1)
                overGame();
            else
                startGame();
        }
    }
}