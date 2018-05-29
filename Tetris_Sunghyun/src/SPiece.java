import processing.core.PApplet;

class SPiece extends Piece {

    SPiece(PApplet pApplet) {
        super(pApplet.color(11, 183, 36), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, pApplet);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}