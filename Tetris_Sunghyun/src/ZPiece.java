import processing.core.PApplet;

class ZPiece extends Piece {
    ZPiece(PApplet pApplet) {
        super(pApplet.color(183, 19, 11), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, pApplet);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}