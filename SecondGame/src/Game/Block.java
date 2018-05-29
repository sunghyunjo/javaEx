package Game;

import processing.core.PApplet;
import java.util.ArrayList;


class Block extends Transform {

    private ArrayList<Block> blocks = new ArrayList<Block>();

    Block() {
        super(Constants.blockX, Constants.blockY, Constants.blockWidth, Constants.blockHeight);
    }

    void readTextFile(PApplet p) {
//        String lines[] = p.loadStrings("/Users/sunghyunCho/documents/foxtail/sunghyun.cho/SecondGame/src/Game/list.txt");
//        for (int i = 0; i < lines.length; i++) {
//            for (int j = 0; j < lines[i].length(); j++) {
//                System.out.println(lines[i].length());
//                if (lines[i].charAt(j) == '1') {
//                    blocks.add(new Block());
//                    Constants.blockX += Constants.blockWidth + Constants.blockSpace;
//                } else if (lines[i].charAt(j) == '*') {
//                    Constants.blockY += Constants.blockHeight + Constants.blockSpace;
//                    Constants.blockX = Constants.blockInitX;
//                } else if (lines[i].charAt(j) == '2') {
//                    blocks.add(new Block());
//                    Constants.blockX += Constants.blockWidth + Constants.blockSpace;
//                }
//            }
   //     }
//        FileReader reader = new FileReader("/Users/sunghyunCho/documents/foxtail/sunghyun.cho/SecondGame/src/Game/list.txt");
//        int ch;
//        while ((ch = reader.read()) != -1) {
//            if (ch == '1') {
//                blocks.add(new Block());
//                Constants.blockX += Constants.blockWidth + Constants.blockSpace;
//            } else if (ch == '\n') {
//                Constants.blockX = 0;
//                Constants.blockY = Constants.blockHeight - Constants.blockSpace;
//                blocks.add(new Block());
//            }
//
//        }
//        reader.close();
    }

//        String lines[] = p.loadStrings("/Users/sunghyunCho/documents/foxtail/sunghyun.cho/SecondGame/src/Game/list.txt");
//        for (int i = 0; i < lines.length; i++) {
//            String[] blockValue = PApplet.split(lines[i], ",");
//            switch(blockValue[i]){
//                case "1":
//                    blocks.add(new Block());
//                    Constants.blockX += Constants.blockWidth + Constants.blockSpace;
//                    break;
//                case "2":
//                    System.out.println("in");
//                    Constants.blockX = 0;
//                    Constants.blockY = Constants.blockHeight - Constants.blockSpace;
//            }
//            if (blockValue[i].equals("1")) {
//                blocks.add(new Block());
//                Constants.blockX += Constants.blockWidth + Constants.blockSpace;
//            }
//            if (blockValue[i].equals("2")) {
//                System.out.println("in");
//                Constants.blockX = 0;
//                Constants.blockY = Constants.blockHeight - Constants.blockSpace;
//            }
//
//        }
//    }

    @Override
    public void draw(PApplet p) {
        for (Block b : blocks) {
            p.fill(Constants.blockColor);
            p.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}

//for (int i = 0; i < map.length; i++) {
//        for (int j = 0; j < map[i].length(); j++) {
//        if (map[i].charAt(j) == '1') {
//              bricksOne.add(new Brick(x, y, 0, 0, 255, 1));
//              x += Constant.BRICK_X_SIZE;
//        } else if (map[i].charAt(j) == '*') {
//              y += Constant.BRICK_Y_SIZE;
//              x = Constant.INIT_BRICK_X;
//        } else if (map[i].charAt(j) == '0') {
//              x += Constant.BRICK_X_SIZE;
//        } else if (map[i].charAt(j) == '2') {
//               bricksOne.add(new Brick(x, y, 255, 0, 251, 2));
//               x += Constant.BRICK_X_SIZE;
//        }
//
//
//        }
//        }
