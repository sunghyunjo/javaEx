
enum TileType {
    TypeI(35,22,220, 4, 4, 1, new boolean[][]{
            {
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
                    false, false, false, false,
            },
            {
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
            },
            {
                    false, false, false, false,
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
            },
            {
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
            }
    }),

    TypeJ(35,35,220, 3, 3, 2, new boolean[][]{
            {
                    true, false, false,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, true,
                    false, true, false,
                    false, true, false,
            },
            {
                    false, false, false,
                    true, true, true,
                    false, false, true,
            },
            {
                    false, true, false,
                    false, true, false,
                    true, true, false,
            }
    }),

    TypeL(220,127,35, 3, 3, 2, new boolean[][]{
            {
                    false, false, true,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, false,
                    false, true, true,
            },
            {
                    false, false, false,
                    true, true, true,
                    true, false, false,
            },
            {
                    true, true, false,
                    false, true, false,
                    false, true, false,
            }
    }),

    TypeO(220,220,35, 2, 2, 2, new boolean[][]{
            {
                    true, true,
                    true, true,
            },
            {
                    true, true,
                    true, true,
            },
            {
                    true, true,
                    true, true,
            },
            {
                    true, true,
                    true, true,
            }
    }),

    TypeS(35,220,35, 3, 3, 2, new boolean[][]{
            {
                    false, true, true,
                    true, true, false,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, false, true,
            },
            {
                    false, false, false,
                    false, true, true,
                    true, true, false,
            },
            {
                    true, false, false,
                    true, true, false,
                    false, true, false,
            }
    }),

    TypeT(128,35,128, 3, 3, 2, new boolean[][]{
            {
                    false, true, false,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, true, false,
            },
            {
                    false, false, false,
                    true, true, true,
                    false, true, false,
            },
            {
                    false, true, false,
                    true, true, false,
                    false, true, false,
            }
    }),

    TypeZ(220,35,35, 3, 3, 2, new boolean[][]{
            {
                    true, true, false,
                    false, true, true,
                    false, false, false,
            },
            {
                    false, false, true,
                    false, true, true,
                    false, true, false,
            },
            {
                    false, false, false,
                    true, true, false,
                    false, true, true,
            },
            {
                    false, true, false,
                    true, true, false,
                    true, false, false,
            }
    });

    private int red;
    private int green;
    private int blue;
    private int spawnCol;
    private int spawnRow;
    private int dimension;
    private int rows;
    private int cols;
    private boolean[][] tiles;

    TileType(int red, int green, int blue, int dimension, int cols, int rows, boolean[][] tiles) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.dimension = dimension;
        this.tiles = tiles;
        this.cols = cols;
        this.rows = rows;

        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = getTopInset(0);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getDimension() {
        return dimension;
    }


    public int getSpawnColumn() {
        return spawnCol;
    }

    public int getSpawnRow() {
        return spawnRow;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }

    public int getLeftInset(int rotation) {

        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (isTile(x, y, rotation)) {
                    return x;
                }
            }
        }
        return -1;
    }

    public int getRightInset(int rotation) {
        for (int x = dimension - 1; x >= 0; x--) {
            for (int y = 0; y < dimension; y++) {
                if (isTile(x, y, rotation)) {
                    return dimension - x;
                }
            }
        }
        return -1;
    }

    public int getTopInset(int rotation) {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (isTile(x, y, rotation)) {
                    return y;
                }
            }
        }
        return -1;
    }

    public int getBottomInset(int rotation) {
        for (int y = dimension - 1; y >= 0; y--) {
            for (int x = 0; x < dimension; x++) {
                if (isTile(x, y, rotation)) {
                    return dimension - y;
                }
            }
        }
        return -1;
    }

}