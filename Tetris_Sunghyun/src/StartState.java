import processing.core.PApplet;
import processing.core.PConstants;

class StartState extends GameState {
    StartState(PApplet tetris, TetrisGame t) {
        super(tetris, t);
    }

    @Override
    public void time() {
    }

    @Override
    public void draw(int posX) {
        tetris.clear();
        tetris.background(0);
        tetris.fill(255);
        tetris.textSize(20);
        tetris.text("Press Alt",posX+50, 300);
    }

    @Override
    public void pressKey() {
        if (tetris.keyCode == PConstants.ALT) {
            t.currentState = t.playingState;
            System.out.println("Enter Alt");
        }
    }
}
