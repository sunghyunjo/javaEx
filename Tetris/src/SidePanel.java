import processing.core.PApplet;

public class SidePanel {

    private Tetris tetris;

    public SidePanel(Tetris tetris) {
        this.tetris = tetris;
    }

    public void paintComponent(PApplet p) {
        int offset;

        p.textSize(16);
        p.text("Stats", Constants.SMALL_INSET, offset = Constants.STATS_INSET);
        p.textSize(12);
        p.text("Level: " + tetris.getLevel(), Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("Score: " + tetris.getScore(), Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);


        p.textSize(16);
        p.text("Controls", Constants.SMALL_INSET, offset = Constants.CONTROLS_INSET);
        p.textSize(12);
        p.text("A - Move Left", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("D - Move Right", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("Q - Rotate Anticlockwise", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("E - Rotate Clockwise", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("S - Drop", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);
        p.text("P - Pause Game", Constants.LARGE_INSET, offset += Constants.TEXT_STRIDE);


        p.textSize(16);
        p.text("Next Piece:", Constants.SMALL_INSET, 70);




        p.color(128, 192, 128);
        //p.fill(0);
        //p.rect(0,0,200,Constants.PANEL_HEIGHT);
        //p.rect(Constants.SQUARE_CENTER_X-Constants.SQUARE_SIZE, Constants.SQUARE_CENTER_Y - Constants.SQUARE_SIZE, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2);

        TileType type = tetris.getNextPieceType();
        if (!tetris.isGameOver() && type != null) {
            int cols = type.getCols();
            int rows = type.getRows();
            int dimension = type.getDimension();

            int startX = (Constants.SQUARE_CENTER_X - (cols * Constants.SIDE_TILE_SIZE / 2));
            int startY = (Constants.SQUARE_CENTER_Y - (rows * Constants.SIDE_TILE_SIZE / 2));

            int top = type.getTopInset(0);
            int left = type.getLeftInset(0);

            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if (type.isTile(col, row, 0)) {
                        drawTile(type, startX + ((col - left) * Constants.SIDE_TILE_SIZE), startY + ((row - top) * Constants.SIDE_TILE_SIZE), p);
                    }
                }
            }

        }
    }

    private void drawTile(TileType type, int x, int y, PApplet p) {
        p.fill(type.getRed(), type.getGreen(), type.getBlue());
        p.rect(x, y, Constants.SIDE_TILE_SIZE, Constants.SIDE_TILE_SIZE);

    }
}
