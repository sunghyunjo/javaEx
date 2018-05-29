import processing.core.PApplet;

class LPiece extends Piece {
    LPiece(PApplet tetris) {
        super(tetris.color(183, 100, 11), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, tetris);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}