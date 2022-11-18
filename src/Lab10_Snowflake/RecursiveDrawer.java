import javax.swing.*;
import java.awt.*;

public abstract class RecursiveDrawer extends JPanel {
    public final Color[] RAINBOW = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

    RecursiveDrawer() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    abstract void draw(Graphics g);

    int getWidth(double scale) {
        return (int) Math.round(getWidth() * scale);
    }
    int getHeight(double scale) {
        return (int) Math.round(getHeight() * scale);
    }

    Color getRandomColor() {
        return RAINBOW[(int) (Math.random() * RAINBOW.length)];
    }
}
