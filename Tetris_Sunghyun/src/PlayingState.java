import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Random;

class PlayingState extends GameState {

    private int[][] gameBoard;
    private long lastFallTime;
    private Piece currentPiece;
    private Piece showNextPiece;
    private Piece nextPiece;
    private int score;
    private float speed;
    private int rotateKeyTime;
    private int moveKeyTime;
    private boolean spaceIn;
    private int combo;
    private Random random = new Random(50);

    PlayingState(PApplet tetris, TetrisGame t) {
        super(tetris, t);
        gameBoard = new int[MakeUser.BOARD_HEIGHT][MakeUser.BOARD_WIDTH];
        fillZerosOnBoard(gameBoard);
        currentPiece = makePiece();
        nextPiece = makePiece();
        showNextPiece = nextPiece;
        lastFallTime = System.currentTimeMillis();
        score = 0;
        speed = 1.0f;
        rotateKeyTime = tetris.millis();
        moveKeyTime = tetris.millis();
        spaceIn = false;
        combo = 0;
    }

    private Piece makePiece() {
        int selection = random.nextInt(7);
        switch (selection) {
            case 0:
                return new IPiece(tetris);
            case 1:
                return new OPiece(tetris);
            case 2:
                return new TPiece(tetris);
            case 3:
                return new ZPiece(tetris);
            case 4:
                return new SPiece(tetris);
            case 5:
                return new JPiece(tetris);
            case 6:
                return new LPiece(tetris);
        }
        return null;
    }

    private void fillZerosOnBoard(int[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    @Override
    public void draw(int posX) {
        tetris.pushMatrix();
        // tetris.scale(((float) tetris.width) / TetrisGame.BOARD_WIDTH, ((float) tetris.height) / TetrisGame.BOARD_HEIGHT);
        tetris.scale(30, 30);
        tetris.translate(.5f + t.anchor, .5f);
        drawBoard();
        currentPiece.draw();
        showNextPiece.drawNextPiece();
        tetris.popMatrix();
        tetris.text("Score :" + score, posX + 10, 50);
        tetris.text("Combo :" + combo, posX + 10, 90);
        tetris.stroke(255);
        tetris.line(posX, 600, posX, 0);
    }

    private void drawBoard() {
        tetris.rectMode(PConstants.CENTER);
        for (int i = 0; i < MakeUser.BOARD_HEIGHT; i++) {
            for (int j = 0; j < MakeUser.BOARD_WIDTH; j++) {
                tetris.stroke(gameBoard[i][j]);
                tetris.rect(j, i, 0, 0);
            }
        }
    }

    @Override
    public void time() {
        int MILLIS_PER_FALL = 300;
        if (System.currentTimeMillis() - lastFallTime >= MILLIS_PER_FALL) {
            if (currentPiece.canFall(gameBoard)) {
                currentPiece.fall(speed);
            } else { //블록이 고정 됬다.
                if (currentPiece.y == 0)
                    t.currentState = new EndState(tetris, t);
                writePiece(currentPiece, gameBoard);
                int beforeScore = score;
                reduceBoard(gameBoard);
                int afterScore = score;

                comboCheck(afterScore, beforeScore);

                changePiece();
                System.out.println(((afterScore - beforeScore) / 10));
            }
            lastFallTime = System.currentTimeMillis();
        }
    }

    private void comboCheck(int after, int before) {
        if (after > before)
            combo++;
        else if (after == before)
            combo = 0;
    }

    //이전 점수보다 애프터 점수가 더 크면 콤보값 ++  이전 점수랑 애프터 점수랑 같으면 콤보 초기화

    private void changePiece() {
        currentPiece = nextPiece;
        showNextPiece = makePiece();
        nextPiece = showNextPiece;
        spaceIn = false;
    }

    private void reduceBoard(int[][] gameBoard) {
        for (int i = 0; i < MakeUser.BOARD_HEIGHT; i++) {
            boolean completeRow = true;
            for (int j = 0; j < MakeUser.BOARD_WIDTH; j++) {
                if (gameBoard[i][j] == 0)
                    completeRow = false;
            }

            if (!completeRow)
                continue;


            score += 10;

            for (int row = i; row > 0; row--) {
                System.arraycopy(gameBoard[row - 1], 0, gameBoard[row], 0, MakeUser.BOARD_WIDTH);
            }

        }
    }

    private void writePiece(Piece currentPiece, int[][] gameBoard) {
        for (int row = 0; row < currentPiece.shape.length; row++) {
            for (int col = 0; col < currentPiece.shape[row].length; col++) {
                if (currentPiece.shape[row][col] == 0)
                    continue;
                gameBoard[(int) currentPiece.y + row][currentPiece.x + col] = currentPiece.color;
            }
        }
    }

    @Override
    public void pressKey() {
        if (tetris.millis() >= rotateKeyTime + 100)
            pressMoveKey();
        if (tetris.millis() >= moveKeyTime + 150)
            pressRotateKey();
    }

    private void pressRotateKey() {
        int keyCode = tetris.keyCode;
        moveKeyTime = tetris.millis();
        if (keyCode == PConstants.UP && !spaceIn) {
            if (currentPiece.canRotateLeft(gameBoard))
                currentPiece.rotateLeft();
        }
        if (keyCode == PConstants.DOWN && !spaceIn) {
            if (currentPiece.canFall(gameBoard))
                currentPiece.fall(speed);
        }
        if (keyCode == 0) {
            spaceIn = true;
            while (currentPiece.canFall(gameBoard)) {
                currentPiece.fall(speed);
            }
        }

    }

    private void pressMoveKey() {
        int keyCode = tetris.keyCode;
        rotateKeyTime = tetris.millis();
        if (keyCode == PConstants.LEFT && !spaceIn) {
            if (currentPiece.canMoveLeft(gameBoard))
                currentPiece.moveLeft();
        }
        if (keyCode == PConstants.RIGHT && !spaceIn) {
            if (currentPiece.canMoveRight(gameBoard))
                currentPiece.moveRight();
        }
    }
}