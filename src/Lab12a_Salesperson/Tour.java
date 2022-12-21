package Lab12a_Salesperson;

public class Tour {
	private Node head;
	private int size;

	/**
	 * create an empty tour
	 */
	public Tour() {
		head = null;
		size = 0;
	}

	/**
	 * print tour (one point per line) to std output
	 */
	public void show() {
		System.out.println(head.p);
		Node current = head.next;
		while(current != head && current != null) {
			System.out.println(current.p);
			current = current.next;
		}
	}

	/**
	 * draw the tour using StdDraw
	 */
	public void draw() {
		Node current = head;
		for(int i = 0; i < size; i++) {
			current.p.drawTo(current.next.p);
			current = current.next;
		}
	}

	/**
	 * return number of nodes in the tour
	 */
	public int size() {
		return size;
	}

	/**
	 * return the total distance "traveled", from start to all nodes and back to start
	 */
	public double distance() {
		if(size <= 1) return 0.0;

		double distance = 0.0;
		Node current = head;
		for(int i = 0; i < size; i++) {
			distance += current.p.distanceTo(current.next.p);
			current = current.next;
		}
		return distance;
	}

	/**
	 * insert p using nearest neighbor heuristic
	 */
	public void insertNearest(Point p) {
		if(size == 0) {
			head = new Node(p);
		}
		else {
			double smallestDist = Integer.MAX_VALUE;
			Node smallestDistNode = null;

			Node current = head;
			for(int i = 0; i < size; i++) {
				double dist = p.distanceTo(current.p);
				if(dist < smallestDist) {
					smallestDist = dist;
					smallestDistNode = current;
				}
				current = current.next;
			}

			Node temp = smallestDistNode.next;
			smallestDistNode.next = new Node(p, temp == null ? head : temp);
		}

		size++;
	}

	/**
	 * insert p using smallest increase heuristic
	 */
	public void insertSmallest(Point p) {
		if(size == 0) {
			head = new Node(p);
		}
		else if(size == 1) {
			head.next = new Node(p);
			head.next.next = head;
		}
		else {
			double smallestInc = Integer.MAX_VALUE;
			Node smallestIncNode = null;

			Node current = head;
			for(int i = 0; i < size; i++) {
				double inc = distance() - current.p.distanceTo(current.next.p) + current.p.distanceTo(p) + p.distanceTo(current.next.p);
				if(inc < smallestInc) {
					smallestInc = inc;
					smallestIncNode = current;
				}
				current = current.next;
			}

			Node temp = smallestIncNode.next;
			smallestIncNode.next = new Node(p, temp == null ? head : temp);
		}

		size++;
	}

	private class Node {
		Point p;
		Node next;

		Node(Point p) {
			this(p, null);
		}

		Node(Point p, Node next) {
			this.p = p;
			this.next = next;
		}
	}

	/*
	public static void main(String[] args) {
		//define 4 points forming a square
		Point a = new Point(100.0, 100.0);
		Point b = new Point(500.0, 100.0);
		Point c = new Point(500.0, 500.0);
		Point d = new Point(100.0, 500.0);
		Tour squareTour = new Tour(a, b, c, d);
		squareTour.show();
		squareTour.draw();
	}
	*/
}