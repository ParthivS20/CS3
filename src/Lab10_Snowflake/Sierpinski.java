package Lab10_Snowflake;

import javax.swing.*;
import java.awt.*;

class SierpinskiPanel extends JPanel {
    final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

    SierpinskiPanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        start(g);
    }

    public void start(Graphics g) {
        //g.setColor(Color.BLUE);
        draw(getWidth(0.5), getHeight(0.5), getWidth(0.5), g, 0);
    }

    public void draw(int x, int y, int size, Graphics g, int c) {
        if(size < 1) return;

        if(c >= colors.length) c = 0;
        g.setColor(colors[c]);

        triangle(x, y, size, g);

        draw(x - size / 2, y + size / 2, size / 2, g, c + 1);
        draw(x + size / 2, y - size / 2, size / 2, g, c + 2);
        draw(x - size / 2, y - size / 2, size / 2, g, c + 3);
    }

    public void triangle(int cX, int cY, int sideLength, Graphics g) {
        g.drawLine(cX, cY, cX, cY - sideLength);
        g.drawLine(cX, cY, cX - sideLength, cY);
        g.drawLine(cX - sideLength, cY, cX, cY - sideLength);
    }

    int getWidth(double scale) {
        return (int) Math.round(getWidth() * scale);
    }

    int getHeight(double scale) {
        return (int) Math.round(getHeight() * scale);
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
