package Lab11_ForestFire;

import java.awt.Color;

class FireCell {
    static final int DIRT = 0, GREEN = 1, BURNING = 2, FINAL = 3;
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

    //new Color(90, 190, 90) -> new Color(240, 40, 40)
    void burn() {
        final int step = 50;
        if (status == BURNING) {
            int r = Math.min(color.getRed() + (240 - 90) / step, 240);
            int g = Math.max(color.getGreen() - (190 - 40) / step, 40);
            int b = Math.max(color.getBlue() - (90 - 40) / step, 40);

            color = new Color(r, g, b);
        }
    }

    void blink() {
        if(status == FINAL) {
            status = BURNING;
            color = new Color(240, 40, 40);
        }
        else {
            status = FINAL;
            color = new Color(152, 0, 0);
        }
    }
}
