import processing.core.PApplet;

abstract class Piece {
    int color;
    int[][] shape;
    int x;
    float y;
    private PApplet tetris;

    Piece(int color, int[][] shape, int x, int y, PApplet tetris) {
        this.color = color;
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.tetris = tetris;
    }

    void draw() {
        tetris.stroke(color);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 0)
                    continue;
                tetris.rect(x + j, y + i, 0, 0);
            }
        }
    }

    void drawNextPiece() {
        tetris.stroke(color);
        tetris.scale(0.5f);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 0)
                    continue;
                tetris.rect(j + 1, y + i + 3, 0, 0);
            }
        }
    }

    void fall(float speed) {
        y += speed;
    }

    boolean canFall(int[][] board) {
        return checkPosition(this.shape, this.y + 1, this.x, board);
    }

    void moveLeft() {
        x--;
    }

    void moveRight() {
        x++;
    }

    private boolean checkPosition(int[][] shape, float i, int j, int[][] board) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 0)
                    continue;
                if (row + i >= MakeUser.BOARD_HEIGHT)
                    return false;
                if (col + j < 0 || col + j >= MakeUser.BOARD_WIDTH)
                    return false;
                if (board[row + (int) i][col + j] != 0)
                    return false;
            }
        }
        return true;
    }

    boolean canMoveLeft(int[][] gameBoard) {
        return checkPosition(this.shape, y, x - 1, gameBoard);
    }

    boolean canMoveRight(int[][] gameBoard) {
        return checkPosition(this.shape, y, x + 1, gameBoard);
    }

    public int[][] rotateLeftMatrix() {
        int[][] transposed = transpose(shape);
        return reverseRows(transposed);
    }

    private int[][] reverseRows(int[][] transposed) {
        int[][] reveresed = new int[transposed.length][transposed[0].length];
        for (int i = 0; i < transposed.length; i++) {
            System.arraycopy(transposed[i], 0, reveresed[transposed.length - 1 - i], 0, transposed.length);
        }
        return reveresed;
    }

    private int[][] transpose(int[][] shape) {
        int[][] transposed = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                transposed[j][i] = shape[i][j];
            }
        }
        return transposed;
    }

    boolean canRotateLeft(int[][] gameBoard) {
        return checkPosition(rotateLeftMatrix(), y, x, gameBoard);
    }

    void rotateLeft() {
        this.shape = rotateLeftMatrix();
    }
}