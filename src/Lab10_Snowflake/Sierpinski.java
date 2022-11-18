import javax.swing.*;
import java.awt.*;

class SierpinskiPanel extends RecursiveDrawer {
    @Override
    void draw(Graphics g) {
        sierpinski(getWidth(0.5), getHeight(0.5), getWidth(0.5), g, 0);
    }

    private void sierpinski(int x, int y, int size, Graphics g, int c) {
        if(size < 1) return;

        if(c >= RAINBOW.length) c = 0;
        g.setColor(RAINBOW[c]);

        triangle(x, y, size, g);

        sierpinski(x - size / 2, y + size / 2, size / 2, g, c + 1);
        sierpinski(x + size / 2, y - size / 2, size / 2, g, c + 2);
        sierpinski(x - size / 2, y - size / 2, size / 2, g, c + 3);
    }

    private void triangle(int cX, int cY, int sideLength, Graphics g) {
        g.drawLine(cX, cY, cX, cY - sideLength);
        g.drawLine(cX, cY, cX - sideLength, cY);
        g.drawLine(cX - sideLength, cY, cX, cY - sideLength);
    }
}

public class Sierpinski {
    public static void main(String[] args) {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Sierpinski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
