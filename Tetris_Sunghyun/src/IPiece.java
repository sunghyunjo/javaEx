import processing.core.PApplet;

class IPiece extends Piece {

    IPiece(PApplet tetris) {
        super(tetris.color(66, 134, 244), getPiece(), MakeUser.BOARD_WIDTH / 2 - 2, 0, tetris);
    }

    private static int[][] getPiece() {
        return new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}