import processing.core.PApplet;

public class MakeUser extends PApplet {
    public static int BOARD_WIDTH;
    static int BOARD_HEIGHT;
    private static int DRAW_SCALE;
    private TetrisGame tetrisGame1;
    private TetrisGame tetrisGame2;
    private TetrisGame tetrisGame3;

    public MakeUser() {
        BOARD_WIDTH = Constants.BOARD_WIDTH;
        BOARD_HEIGHT = Constants.BOARD_HEIGHT;
        DRAW_SCALE = Constants.DRAW_SCALE;
    }

    @Override
    public void settings() {
        size(BOARD_WIDTH * DRAW_SCALE * 3, BOARD_HEIGHT * DRAW_SCALE);
    }

    @Override
    public void setup() {
        tetrisGame1 = new TetrisGame(0, this);
        tetrisGame2 = new TetrisGame(10, this);
        tetrisGame3 = new TetrisGame(20, this);

    }

    public static void main(String[] args) {
        new MakeUser().runSketch();
    }

    @Override
    public void draw() {
        clear();
        tetrisGame1.draw(0);
        tetrisGame2.draw(300);
        tetrisGame3.draw(600);
        if (keyPressed) {
            tetrisGame1.pressKey();
            tetrisGame2.pressKey();
            tetrisGame3.pressKey();
        }
    }


}
