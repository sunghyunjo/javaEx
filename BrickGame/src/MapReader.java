import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class MapReader {
    private File file;
    private FileReader map;
    private List<Brick> brickList;
    private int brickNum = 0;
    private int countX = 0;
    private int countY = 0;

    MapReader(List<Brick> brickList) {
        this.brickList = brickList;
    }

    void readFile() {
        try {
            file = new File("/Users/sunghyunCho/documents/foxtail/sunghyun.cho/BrickGame/map1.txt");
            map = new FileReader("/Users/sunghyunCho/documents/foxtail/sunghyun.cho/BrickGame/map1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    long getFileLength() {
        return file.length();
    }

    void read() {
        try {
            int propertyInt;
            while ((propertyInt = map.read()) != -1) {
                char property = (char) propertyInt;
                brickList.get(brickNum).setX(countX * Constant.brickWidth);
                brickList.get(brickNum).setY(countY * Constant.brickHeight);

                if (property == '\n') {
                    countY++;
                    brickList.get(brickNum).item = -1;
                } else if (propertyInt == 13) {
                    countX = 0;
                    brickList.get(brickNum).item = -1;
                } else if (property == ' ') {
                    countX++;
                    brickList.get(brickNum).item = -1;
                } else {
                    brickList.get(brickNum).drawCheck = true;
                    countX++;
                    if (property == '0') {
                        brickList.get(brickNum).item = 0;
                    } else if (property == '1') {
                        brickList.get(brickNum).item = 1;
                    } else if (property == '2') {
                        brickList.get(brickNum).item = 2;
                    } else if (property == '3') {
                        brickList.get(brickNum).item = 3;
                    } else if (property == '4') {
                        brickList.get(brickNum).item = 4;
                    }
                }
                brickNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
