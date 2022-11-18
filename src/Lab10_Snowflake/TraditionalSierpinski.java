import javax.swing.*;
import java.awt.*;

class TraditionalSierpinskiPanel extends RecursiveDrawer {
    @Override
    void draw(Graphics g) {
        traditionalSierpinski(getWidth(0.5), getHeight(), getWidth(0.5), g, 0);
    }

    private void traditionalSierpinski(int x, int y, int size, Graphics g, int c) {
        if(size < 1) return;

        if(c >= RAINBOW.length) c = 0;
        g.setColor(RAINBOW[c]);

        triangle(x, y, size, g);

        traditionalSierpinski(x - size / 2, y, size / 2, g, c + 1);
        traditionalSierpinski(x + size / 2, y, size / 2, g, c + 2);
        traditionalSierpinski(x, y - (int) (size * Math.sqrt(3) / 2), size / 2, g, c + 3);
    }

    private void triangle(int cX, int cY, int sideLength, Graphics g) {
        g.drawLine(cX, cY, cX - sideLength / 2, cY - (int) (sideLength * Math.sqrt(3) / 2));
        g.drawLine(cX, cY, cX + sideLength / 2, cY - (int) (sideLength * Math.sqrt(3) / 2));
        g.drawLine(cX - sideLength / 2, cY -(int) (sideLength * Math.sqrt(3) / 2), cX + sideLength / 2, cY - (int) (sideLength * Math.sqrt(3) / 2));
    }
}

public class TraditionalSierpinski {
    public static void main(String[] args) {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Traditional Sierpinski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TraditionalSierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}