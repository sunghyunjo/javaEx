public class Color implements Cloneable {
    private int red;
    private int green;
    private int blue;
    private int gray;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.gray = 255;
    }

   @Override
    public Color clone() {
        try {
            Color color = (Color) super.clone();
            return color;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void controlRGB(int colorLevel, char colorCode) {
        int color;
        color = getColor(colorCode);

        if (colorLevel < 0)
            color += 10;
        else
            color -= 10;

        limitColor(color);
        setColor(colorCode, color);
    }

    public void controlGray(int colorLevel) {
        if (colorLevel < 0)
            this.gray += 3;
        else
            this.gray -= 3;

        limitColor(this.gray);
        this.red = this.gray;
        this.green = this.gray;
        this.blue = this.gray;
    }

    public int limitColor(int colorCode) {
        if (colorCode > 255)
            colorCode = 255;
        else if (colorCode < 0)
            colorCode = 0;

        return colorCode;
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

    public int getColor(char colorCode) {
        int color = 0;
        if (colorCode == 'r')
            color = this.red;
        else if (colorCode == 'g')
            color = this.green;
        else if (colorCode == 'b')
            color = this.blue;

        return color;

    }

    public void setColor(char colorCode, int color) {
        if (colorCode == 'r')
            this.red = color;
        else if (colorCode == 'g')
            this.green = color;
        else
            this.blue = color;
    }
}
