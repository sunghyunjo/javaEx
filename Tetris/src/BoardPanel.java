
import processing.core.PApplet;

class BoardPanel {

    private Tetris tetris;
    private TileType[][] tiles;

    BoardPanel(Tetris tetris) {
        this.tetris = tetris;
        tiles = new TileType[Constants.ROW_COUNT][Constants.COL_COUNT];
    }

    void clear() {
        for (int i = 0; i < Constants.ROW_COUNT; i++) {
            for (int j = 0; j < Constants.COL_COUNT; j++) {
                tiles[i][j] = null;
            }
        }
    }

    boolean isValidAndEmpty(TileType type, int x, int y, int rotation) {
        if (x < -type.getLeftInset(rotation) || x + type.getDimension() - type.getRightInset(rotation) >= Constants.COL_COUNT)
            return false;

        if (y < -type.getTopInset(rotation) || y + type.getDimension() - type.getBottomInset(rotation) >= Constants.ROW_COUNT)
            return false;

        for (int col = 0; col < type.getDimension(); col++) {
            for (int row = 0; row < type.getDimension(); row++) {
                if (type.isTile(col, row, rotation) && isOccupied(x + col, y + row)) {
                    return false;
                }
            }
        }
        return true;
    }

    void addPiece(TileType type, int x, int y, int rotation) {
        for (int col = 0; col < type.getDimension(); col++) {
            for (int row = 0; row < type.getDimension(); row++) {
                if (type.isTile(col, row, rotation)) {
                    setTile(col + x, row + y, type);
                }
            }
        }
    }

    int checkLines() {
        int completedLines = 0;

        for (int row = 0; row < Constants.ROW_COUNT; row++) {
            if (checkLine(row)) {
                completedLines++;
            }
        }
        return completedLines;
    }

    private boolean checkLine(int line) {
        for (int col = 0; col < Constants.COL_COUNT; col++) {
            if (!isOccupied(col, line)) {
                return false;
            }
        }

        for (int row = line - 1; row >= 0; row--) {
            for (int col = 0; col < Constants.COL_COUNT; col++) {
                setTile(col, row + 1, getTile(col, row));
            }
        }
        return true;
    }

    private boolean isOccupied(int x, int y) {
        return tiles[y][x] != null;
    }

    private void setTile(int x, int y, TileType type) {
        tiles[y][x] = type;
    }

    private TileType getTile(int x, int y) {
        return tiles[y][x];
    }

    void paintComponent(PApplet p) {
        if (tetris.isPaused()) {
            p.textSize(16);
            p.text("PAUSED", Constants.CENTER_X, Constants.CENTER_Y);
        } else if (tetris.isNewGame() || tetris.isGameOver()) {
            String msg = tetris.isNewGame() ? "TETRIS" : "GAME OVER";
            p.text(msg, Constants.CENTER_X, 150);
            p.textSize(12);
            msg = "Press Enter to Play" + (tetris.isNewGame() ? "" : "Again");
            p.text(msg, Constants.CENTER_X, 300);
        } else {
            for (int x = 0; x < Constants.COL_COUNT; x++) {
                for (int y = Constants.HIDDEN_ROW_COUNT; y < Constants.ROW_COUNT; y++) {
                    TileType tile = getTile(x, y);
                    if (tile != null) {
                        drawTile(tile, x * Constants.TILE_SIZE, (y - Constants.HIDDEN_ROW_COUNT) * Constants.TILE_SIZE, p);
                    }
                }
            }

            TileType type = tetris.getPieceType();
            int pieceCol = tetris.getPieceCol();
            int pieceRow = tetris.getPieceRow();
            int rotation = tetris.getPieceRotation();

            for (int col = 0; col < type.getDimension(); col++) {
                for (int row = 0; row < type.getDimension(); row++) {
                    if (pieceRow + row >= 2 && type.isTile(col, row, rotation)) {
                        drawTile(type, (pieceCol + col) * Constants.TILE_SIZE, (pieceRow + row - Constants.HIDDEN_ROW_COUNT) * Constants.TILE_SIZE, p);
                    }
                }
            }

            for (int lowest = pieceRow; lowest < Constants.ROW_COUNT; lowest++) {
                if (isValidAndEmpty(type, pieceCol, lowest, rotation)) {
                    continue;
                }

                lowest--;

                break;
            }

            p.fill(100);
            for (int x = 0; x < Constants.COL_COUNT; x++) {
                for (int y = 0; y < Constants.VISIBLE_ROW_COUNT; y++) {
                    p.line(0, y * Constants.TILE_SIZE, Constants.COL_COUNT * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
                    p.line(x * Constants.TILE_SIZE, 0, x * Constants.TILE_SIZE, Constants.VISIBLE_ROW_COUNT * Constants.TILE_SIZE);
                }
            }
        }


        p.fill(0);
        p.rect(0, 0, Constants.TILE_SIZE * Constants.COL_COUNT, Constants.TILE_SIZE * Constants.VISIBLE_ROW_COUNT);
    }

    private void drawTile(TileType type, int x, int y, PApplet p) {
        drawTile(type.getRed(), type.getGreen(), type.getBlue(), x, y, p);
    }

    private void drawTile(int red, int green, int blue, int x, int y, PApplet p) {
        p.fill(red, green, blue);
        p.rect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
}
