import processing.core.PApplet;

import java.util.Random;

public class Tetris extends PApplet {

    private BoardPanel board;
    private SidePanel side;
    private boolean isPaused;
    private boolean isNewGame;
    private boolean isGameOver;
    private int level;
    private int score;
    private Random random;
    //private Clock logicTimer;
    private TileType currentType;
    private TileType nextType;
    private int currentColumn;
    private int currentRow;
    private int currentRotation;
    private int dropCooldown;
    private float gameSpeed;

    private Tetris() {
        board = new BoardPanel(this);
        //side = new SidePanel(this);
    }

    public static void main(String args[]) {
        Tetris tetris = new Tetris();
        tetris.runSketch();
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw(){
        background(255);
        startGame();
    }

    @Override
    public void keyPressed() {
        switch (keyCode) {
            case DOWN:
                if (!isPaused && dropCooldown == 0) {
                    //logicTimer.setCyclesPerSecond(25.0f);
                }
                break;

            case LEFT:
                if (!isPaused && board.isValidAndEmpty(currentType, currentColumn - 1, currentRow, currentRotation)) {
                    currentColumn--;
                }
                break;

            case RIGHT:
                if (!isPaused && board.isValidAndEmpty(currentType, currentColumn + 1, currentRow, currentRotation)) {
                    currentColumn++;
                }
                break;

            case 'q':
                if (!isPaused) {
                    rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
                }
                break;

            case 'e':
                if (!isPaused) {
                    rotatePiece((currentRotation == 3) ? 0 : currentRotation + 1);
                }
                break;

            case 'p':
                if (!isGameOver && !isNewGame) {
                    isPaused = !isPaused;
                  //  logicTimer.setPaused(isPaused);
                }
                break;

            case ENTER:
                System.out.println("Enter");
                if (isGameOver || isNewGame)
                    resetGame();
                break;

        }
    }

    @Override
    public void keyReleased() {
        if (keyCode == DOWN) {
//            logicTimer.setCyclesPerSecond(gameSpeed);
//            logicTimer.reset();
        }
    }

    public void startGame() {
        this.random = new Random();
        this.isNewGame = true;
        this.gameSpeed = 1.0f;

        renderGame();
        updateGame();
//        this.logicTimer = new Clock(gameSpeed);
//        logicTimer.setPaused(true);

//        while (true) {
//
//            long start = System.nanoTime();
//
//            logicTimer.update();
//
//            if (logicTimer.hasElapsedCycle()) {
//                updateGame();
//            }
//
//            renderGame();
//
//            long delta = (System.nanoTime() - start) / 1000000L;
//
//            if (delta < Constants.FRAME_TIME) {
//                try {
//                    Thread.sleep(Constants.FRAME_TIME - delta);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    public void updateGame() {
        if (board.isValidAndEmpty(currentType, currentColumn, currentRow + 1, currentRotation)) {
            currentRow++;
        } else {
            board.addPiece(currentType, currentColumn, currentRow, currentRotation);
            int cleared = board.checkLines();
            if (cleared > 0) {
                score += 50 << cleared;
            }

            gameSpeed += 0.035f;
//            logicTimer.setCyclesPerSecond(gameSpeed);
//            logicTimer.reset();
            dropCooldown = 25;
            level = (int) (gameSpeed * 1.70f);

            spawnPiece();
        }
    }


    public void renderGame() {
        board.paintComponent(this);
        //side.paintComponent(this);
    }

    private void rotatePiece(int newRotation) {

        int newColumn = currentColumn;
        int newRow = currentRow;
        int left = currentType.getLeftInset(newRotation);
        int right = currentType.getRightInset(newRotation);
        int top = currentType.getTopInset(newRotation);
        int bottom = currentType.getBottomInset(newRotation);

        if (currentColumn < -left) {
            newColumn -= currentColumn - left;
        } else if (currentColumn + currentType.getDimension() - right >= Constants.COL_COUNT) {
            newColumn -= (currentColumn + currentType.getDimension() - right) - Constants.COL_COUNT + 1;
        }

        if (currentRow < -top) {
            newRow -= currentRow - top;
        } else if (currentRow + currentType.getDimension() - bottom >= Constants.ROW_COUNT) {
            newRow -= (currentRow + currentType.getDimension() - bottom) - Constants.ROW_COUNT + 1;
        }

        if (board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) {
            currentRotation = newRotation;
            currentRow = newRow;
            currentColumn = newColumn;
        }
    }

    private void resetGame() {
        level = 1;
        score = 0;
        gameSpeed = 1.0f;
        nextType = TileType.values()[random.nextInt(Constants.TYPE_COUNT)];
        isNewGame = false;
        isGameOver = false;
        board.clear();
//        logicTimer.reset();
//        logicTimer.setCyclesPerSecond(gameSpeed);
        spawnPiece();
    }

    private void spawnPiece() {
        currentType = nextType;
        currentColumn = currentType.getSpawnColumn();
        currentRow = currentType.getSpawnRow();
        currentRotation = 0;
        nextType = TileType.values()[random.nextInt(Constants.TYPE_COUNT)];

        if (!board.isValidAndEmpty(currentType, currentColumn, currentRow, currentRotation)) {
            this.isGameOver = true;
          //  logicTimer.setPaused(true);
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isNewGame() {
        return isNewGame;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public TileType getPieceType() {
        return currentType;
    }

    public TileType getNextPieceType() {
        return nextType;
    }

    public int getPieceCol() {
        return currentColumn;
    }

    public int getPieceRow() {
        return currentRow;
    }

    public int getPieceRotation() {
        return currentRotation;
    }


}

