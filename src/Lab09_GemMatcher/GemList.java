package Lab09_GemMatcher;

public class GemList {
	private Node head;
	private Node tail;
	private int size;

	int size() {
		return size;
	}

	void draw(double y) {
		Node current = head;
		for(int i = 0; i < size; i++) {
			if(current != null && current.gem != null) {
				current.gem.draw(GemGame.indexToX(i), y);
				current = current.next;
			}
		}
	}

	@Override
	public String toString() {
		if(head == null) return "<none>";

		String out = "";
		Node current = head;
		while(current != null) {
			out += current.gem.getType();
			if(current.next != null) out += " -> ";
			current = current.next;
		}
		return out;
	}

	Gem get(int index) {
		Node current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.gem;
	}

	void bomb(int index) {
		/*if(size == 1) {
			size--;
			return;
		}

		if(size == 2) {
			head = null;
			size --;
		}

		/*Node current = head;
		for(int i = 0; i < index - 2; i++) {
			current = current.next;
		}
		System.out.println(current.gem);*/
	}

	void insertBefore(Gem gem, int index) {
		size++;

		if(size == 1) {
			head = tail = new Node(gem);
			return;
		}

		if(index == 1) {
			Node temp = head;
			head = new Node(gem);
			head.next = temp;
			return;
		}

		if(index >= size) {
			tail.next = new Node(gem);
			tail = tail.next;
			return;
		}

		Node current = head;
		for(int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		Node temp = current.next;
		current.next = new Node(gem);
		current.next.next = temp;
	}

	int score() {
		if(size == 0) return 0;

		int score = 0;
		int multiplierScore = 0;
		int multiplier = 1;
		Node prevGem = null;

		Node current = head;
		while(current != null) {
			if(prevGem != null && current.gem.getType() == prevGem.gem.getType()) {
				if(multiplier == 1) {
					score -= prevGem.gem.getPoints();
					multiplierScore += prevGem.gem.getPoints();
				}

				multiplier++;
				multiplierScore += current.gem.getPoints();
			}
			else {
				if(multiplier > 1) {
					score += multiplier * multiplierScore;
					multiplierScore = 0;
					multiplier = 1;
				}

				score += current.gem.getPoints();
			}

			prevGem = current;
			current = current.next;
		}

		if(multiplier > 1) {
			score += multiplier * multiplierScore;
		}
		return score;
	}

	private static class Node {
		private final Gem gem;
		private Node next;

		Node(Gem gem) {
			this.gem = gem;
		}
	}

	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}	
}
