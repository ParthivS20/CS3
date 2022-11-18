package Lab10_Snowflake;

import javax.swing.*;
import java.awt.*;

class RecursiveTreesPanel extends RecursiveDrawer {
    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        recursiveTrees(getWidth(0.5), getHeight(),40, 175, 0, g);
    }

    private void recursiveTrees(int x, int y, int width, int height, int angle, Graphics g) {
        if(width < 1 || height < 1) return;

        ((Graphics2D) g).setStroke(new BasicStroke(width));

        g.drawLine(x, y, x, y - height);
        g.setColor(getRandomColor());
    }
}

public class RecursiveTrees {
    public static void main(String[] args) {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Recursive Trees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RecursiveTreesPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
