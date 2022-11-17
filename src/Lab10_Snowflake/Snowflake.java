package Lab10_Snowflake;

import javax.swing.*;
import java.awt.*;

class SnowFlakePanel extends JPanel {
	SnowFlakePanel() {
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blizzard(g);
	}

	void blizzard(Graphics g) {
		double margin = 0.01;
		for(int i = 0; i < 30; i++) {
			double size = randomPercent(0.02, 0.1);
			double centerX = randomPercent(margin + size, 1 - (margin + size));
			double centerY = randomPercent(margin + size, 1 - (margin + size));
			double decay = randomPercent(0.2, 0.42);

			g.setColor(randomColor());
			draw(getWidth(centerX), getHeight(centerY), getWidth(size), decay, g);
		}
	}

	private void draw(int x, int y, int lineSize, double decay, Graphics g) {
		int newLineSize = (int) (decay * lineSize);
		if(newLineSize < 1) return;

		for(int i = 0; i < 361; i += 60) {
			int newX = (int) (lineSize * Math.cos(Math.toRadians(i))) + x;
			int newY = (int) (lineSize * Math.sin(Math.toRadians(i))) + y;
			g.drawLine(x, y, newX, newY);
			draw(newX, newY, newLineSize, decay, g);
		}
	}

	private int getWidth(double scale) {
		return (int) Math.round(getWidth() * scale);
	}

	private int getHeight(double scale) {
		return (int) Math.round(getHeight() * scale);
	}

	private double randomPercent(double min, double max) {
		return Math.random() * (max - min) + min;
	}

	private Color randomColor() {
		return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	}
}

public class Snowflake {
	public static void main ( String[] args ) {
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
