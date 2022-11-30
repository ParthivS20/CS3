package Lab10_Snowflake;

import javax.swing.*;
import java.awt.*;

class PolygonsPanel extends RecursiveDrawer {
    private int n;
    PolygonsPanel(int n) {
        super();
        this.n = n;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        polygons(getWidth(0.5), getHeight(0.5),200, 0, g);
    }

    private void polygons(int x, int y, int size, int angle, Graphics g) {
        if(size < 2) return;
        g.setColor(getRandomColor());
        g.drawPolygon(getPolygon(x, y, size, angle));
        polygons(x - size / 2, y, size / 2, angle + 10, g);
        polygons(x + size / 2, y, size / 2, angle + 10, g);
        polygons(x, y - size / 2, size / 2, angle + 10, g);
        polygons(x, y + size / 2, size / 2, angle + 10, g);
    }

    private Polygon getPolygon(int x, int y, int size, int angle) {
        int[] xs = new int[n];
        int[] ys = new int[n];

        for(int i = 0; i < n; i++) {
            int a = i * (360 / n) + angle + (360 / n / 2);
            xs[i] = (int) (Math.sin(Math.toRadians(a)) * size) + x;
            ys[i] = (int) (Math.cos(Math.toRadians(a)) * size) + y;
        }
        return new Polygon(xs, ys, n);
    }
}

public class Polygons {
    public static void main(String[] args) {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Polygons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PolygonsPanel(9));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
