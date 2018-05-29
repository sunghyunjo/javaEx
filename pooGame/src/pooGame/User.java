package pooGame;

import processing.core.PApplet;

public class User extends Transform {
    private int energy;

    public User() {
        super(Constants.userX, Constants.userY, Constants.userWidth, Constants.userHeight);
        setAccel(Constants.accelX, Constants.accelY);
        energy = Constants.userEnergy;
    }

    @Override
    public void draw(PApplet p) {
        p.rect(getX(), getY(), getWidth(), getHeight());
    }

    public void changeEnergy() {
        energy -= Constants.downEnergy;
        if (energy < 0)
            energy = 0;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
