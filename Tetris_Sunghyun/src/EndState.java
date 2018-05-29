import processing.core.PApplet;
import processing.core.PConstants;

class EndState extends GameState {
    EndState(PApplet tetris, TetrisGame t) {
        super(tetris, t);
    }

    public void draw(int posX) {
        tetris.clear();
        tetris.background(0);
        tetris.fill(255);
        tetris.textSize(20);
        tetris.text("End! Press ALT", posX + 50, 300);
    }

    @Override
    public void time() {
    }

    @Override
    public void pressKey() {
        if (tetris.keyCode == PConstants.ALT) {
            t.playingState = new PlayingState(tetris, t);
            t.currentState = t.playingState;
        }
    }
}