import processing.core.PApplet;

class OPiece extends Piece {

    OPiece(PApplet tetris) {
        super(tetris.color(211, 211, 46), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, tetris);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }

    @Override
    public int[][] rotateLeftMatrix() {
        return this.shape;
    }
}