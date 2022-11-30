package Lab11_ForestFire;

import java.awt.Color;

class FireCell {
    static final int DIRT = 0, GREEN = 1, BURNING = 2;
    private int status;
    Color color;

    FireCell() {
        status = DIRT;
        color = new Color(240, 240, 180);
        if ( Math.random() <= 0.60 ) {
            status = GREEN;
            color = new Color(90, 190, 90);
        }
    }

    int getStatus()
    {
        return status;
    }

    void setStatus(int n)
    {
        status = n;
    }

    boolean light() {
        if(getStatus() == GREEN) {
            setStatus(BURNING);
            return true;
        }

        return false;
    }

    void updateColor() {

    }
}
