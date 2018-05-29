import processing.core.PApplet;

class JPiece extends Piece {
    JPiece(PApplet tetris) {
        super(tetris.color(11, 45, 183), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, tetris);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}