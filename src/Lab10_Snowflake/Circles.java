import javax.swing.*;
import java.awt.*;

class CirclesPanel extends RecursiveDrawer {
    @Override
    void draw(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        circles(getWidth(0.45), getWidth(0.5), getHeight(0.5), g);
    }

    private void circles(int radius, int x, int y, Graphics g) {
        if(radius < 2) return;
        radius *= 2;

        g.setColor(getRandomColor());

        g.drawOval(x - radius / 2, y - radius / 2, radius, radius);
        circles(radius / 4, x - radius / 4, y, g);
        circles(radius / 4, x + radius / 4, y, g);
        circles(radius / 4, x, y - radius / 4, g);
        circles(radius / 4, x, y + radius / 4, g);
    }
}

public class Circles {
    public static void main(String[] args) {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Circles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CirclesPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
