import processing.core.PApplet;

abstract class GameState {
    PApplet tetris;
    TetrisGame t;
    private int[] statePixels;

    GameState(PApplet tetris, TetrisGame t) {
        this.t = t;
        this.tetris = tetris;
        tetris.loadPixels();
        statePixels = new int[tetris.pixels.length];
        tetris.updatePixels();
    }

    public void draw(int anchor) {
        tetris.loadPixels();
        System.arraycopy(statePixels, 0, tetris.pixels, 0, statePixels.length);
        tetris.updatePixels();
    }

    public void time() {
    }

    public abstract void pressKey();
}