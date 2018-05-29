import processing.core.PApplet;

class TetrisGame {

    private GameState startState;
    GameState playingState;
    GameState currentState;
    float anchor;

    TetrisGame(float anchor, PApplet p) {
        this.anchor = anchor;
        initStates(p);
        currentState = startState;
    }

    private void initStates(PApplet p) {
        startState = new StartState(p, this);
        playingState = new PlayingState(p, this);
    }

    void draw(int anchor) {
        currentState.draw(anchor);
        currentState.time();
    }

    void pressKey() {
        currentState.pressKey();
    }

}