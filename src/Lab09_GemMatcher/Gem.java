package Lab09_GemMatcher;

import java.awt.Font;
import java.util.ArrayList;
import java.util.EnumSet;

public class Gem {
	private static final ArrayList<GemType> gemChoices = new ArrayList<>();
	private static final int[] pointChoices = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

	private final GemType type;
	private final int points;

	Gem() {
		this(null, 0);
	}

	Gem(GemType type, int points) {
		if(type == null) {
			if(gemChoices.size() == 0) {
				gemChoices.addAll(EnumSet.allOf(GemType.class));
				int s = gemChoices.size();
				for(int i = 0; i < s; i++) {
					for(int j = 0; j < gemChoices.get(i).chance - 1; j++) {
						gemChoices.add(gemChoices.get(i));
					}

					if(gemChoices.get(i).chance == 0) {
						gemChoices.remove(i);
						i--;
						s--;
					}
				}
			}

			this.type = gemChoices.get((int) ((Math.random() * gemChoices.size())));
			this.points = pointChoices[(int) ((Math.random() * pointChoices.length))];
		}
		else {
			this.type = type;
			this.points = points;
		}
	}

	@Override
	public String toString() {
		return type + " " + points;
	}

	GemType getType() {
		return type;
	}

	int getPoints() {
		return points;
	}

	void draw(double x, double y) {
		StdDraw.picture(x, y, "gems/gem_" + String.valueOf(type).toLowerCase() + ".png");
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("Courier", Font.BOLD,16));
		StdDraw.text(x, y, String.valueOf(points));
	}

	void placed(GemList list, int index) {
		return;
	}

	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;

		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
