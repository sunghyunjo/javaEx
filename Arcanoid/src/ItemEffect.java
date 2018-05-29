import processing.core.PApplet;

enum ItemEffect {
    NORMAL {
        @Override
        void fill(PApplet p) {
            p.fill(255);
        }
    },
    FAST {
        @Override
        void run(Ball balls) {
            balls.changeSpeed(Constants.BALL_SPEED + 2);
            Time.setSpeedItemStartTime(System.currentTimeMillis());
        }

        @Override
        void fill(PApplet p) {
            p.fill(125, 125, 0);
        }
    },
    SLOW {
        @Override
        void run(Ball balls) {
            balls.changeSpeed(Constants.BALL_SPEED - 2);
            Time.setSpeedItemStartTime(System.currentTimeMillis());
        }

        @Override
        void fill(PApplet p) {
            p.fill(0, 125, 125);
        }
    },
    POWERUP {
        @Override
        void run(Ball balls) {
            Ball.setPower(true);
            Time.setPowerItemStartTime(System.currentTimeMillis());
        }

        @Override
        void fill(PApplet p) {
            p.fill(125, 0, 125);
        }
    },
    ADDBALL {
        @Override
        void fill(PApplet p) {
            p.fill(0, 255, 0);
        }
    };

    void run(Ball balls) {
    }

    abstract void fill(PApplet p);
}
