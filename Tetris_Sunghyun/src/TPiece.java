import processing.core.PApplet;

class TPiece extends Piece {

    TPiece(PApplet pApplet) {
        super(pApplet.color(130, 13, 130), getShape(), MakeUser.BOARD_WIDTH / 2 - 2, 0, pApplet);
    }

    private static int[][] getShape() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}