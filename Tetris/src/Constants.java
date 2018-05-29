class Constants {
    static final long FRAME_TIME = 1000L / 50L;
    static final int TYPE_COUNT = TileType.values().length;

     static final int BORDER_WIDTH = 5;

    static final int COL_COUNT = 10;

     static final int VISIBLE_ROW_COUNT = 20;

     static final int HIDDEN_ROW_COUNT = 2;

    static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;

    static final int TILE_SIZE = 24;

    private static final int SHADE_WIDTH = 4;

    static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;

    static final int CENTER_Y = VISIBLE_ROW_COUNT * TILE_SIZE / 2;

    static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;

    static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;

    static final int SIDE_TILE_SIZE = TILE_SIZE >> 1;
    static final int SIDE_SHADE_WIDTH = SHADE_WIDTH >> 1;
    private static final int TILE_COUNT = 5;
    static final int SQUARE_CENTER_X = 130;
    static final int SQUARE_CENTER_Y = 65;
    public static final int SQUARE_SIZE = (SIDE_TILE_SIZE * TILE_COUNT >> 1);
    static final int SMALL_INSET = 20;
     static final int LARGE_INSET = 40;

     static final int STATS_INSET = 175;

     static final int CONTROLS_INSET = 300;

     static final int TEXT_STRIDE = 25;


}
