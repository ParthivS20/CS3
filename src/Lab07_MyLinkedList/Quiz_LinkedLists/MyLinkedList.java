package Lab07_MyLinkedList.Quiz_LinkedLists;

class MyLinkedList {
	public static final int ID = 209389; //add your student ID here
		
	private ListNode front;

	public class ListNode { //******** INNER CLASS ********
		int      data;
		ListNode next;

		public ListNode(int data) {
			this.data = data;
		}
		public ListNode(int data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return "" + this.data;
		}
	}

	public MyLinkedList() { //not actually necessary but included for clarity
		front = null;
	}

	public MyLinkedList(int val) {
		front = new ListNode(val);
	}

	/** for ease of testing, you can construct a MLL object with initial values:
	 *     MyLinkedList list = new MyLinkedList(1, 2, 3, 4);
	 *    
	 *     if this doesn't make sure, simply ignore this constructor!
	 */
	private MyLinkedList(int... vals) {
		if (front == null) front = new ListNode(vals[0]);

		ListNode current = front;

		for (int i = 1; i < vals.length; i++) {
			current.next = new ListNode(vals[i]);
			current = current.next;
		}
	}

	@Override
	public String toString() {
		String result = "[";

		ListNode current = front;

		while (current != null) {
			if (current.next == null) { //reached last element in the list
				result += current.data;
			} else {
				result += current.data + ", ";
			}

			current = current.next;
		}

		result += "]";

		return result;
	}

	public ListNode getFront() {
		return this.front;
	}

	/********************************************
	 ********** QUIZ METHODS BELOW HERE *********
	 ********************************************/

	//#1
	public void replaceLast(int n) {
		ListNode current = front;

		while(current != null) {
			if(current.next == null) {
				current.data = n;
			}
			current = current.next;
		}
	}

	//#2
	public int lastIndexOf(int val) {
		ListNode current = front;

		int i = -1;
		int c = 0;

		while(current != null) {
			if(current.data == val) {
				i = c;
			}
			c++;
			current = current.next;
		}

		return i;
	}

	//#3
	public int countDuplicates() {
		ListNode current = front;
		int d = current.data;
		current = current.next;
		int n = 0;

		while(current != null) {
			if(d == current.data) n++;
			d = current.data;
			current = current.next;
		}

		return n;
	}

	//#4
	public void stutter() {
		ListNode current = front;

		while(current != null) {
			ListNode newNode = new ListNode(current.data);
			ListNode temp = current.next;

			current.next = newNode;
			current.next.next = temp;

			current = temp;
		}
	}

	//#5
	public void removeAll(int n) {
		if(front.data == n) front = front.next;

		if(front == null) return;

		ListNode current = front;

		while (current != null) {
			if(current.next != null && current.next.data == n) current.next = current.next.next;
			current = current.next;
		}
	}

	//#6
	public int deleteLast() {
		ListNode current = front;

		while(current != null) {
			if(current.next.next == null) {
				int n = current.next.data;
				current.next = null;
				return n;
			}

			current = current.next;
		}

		return -1;
	}

	/** You can test your methods below if you'd like */
	public static void main(String[] args) {
		//#1 replaceLast()
		MyLinkedList q1 = new MyLinkedList(1, 2, 3, 4, 5, 6);
		q1.replaceLast(42);
		System.out.println(q1); // [1, 2, 3, 4, 5, 42]
		System.out.println();

		//#2 countDuplicates()
		MyLinkedList q2 = new MyLinkedList(1, 1, 1, 3, 3, 6, 9, 15, 15, 23, 23, 23, 40, 40);
		System.out.println(q2.countDuplicates()); // 7
		System.out.println();

		//#3 lastIndexOf(int n)
		MyLinkedList q3 = new MyLinkedList(1, 18, 2, 7, 18, 39, 18, 40);
		System.out.println(q3.lastIndexOf(18)); // 6
		System.out.println(q3.lastIndexOf(3)); // -1
		System.out.println();

		//#4 stutter()
		MyLinkedList q4 = new MyLinkedList(1, 8, 19, 4, 17);
		q4.stutter();
		System.out.println(q4); // [1, 1, 8, 8, 19, 19, 4, 4, 17, 17]
		System.out.println();

		//#5 removeAll(int n)
		MyLinkedList q5 = new MyLinkedList(3, 9, 4, 2, 3, 8, 17, 4, 3, 18);
		q5.removeAll(3);
		System.out.println(q5); // [9, 4, 2, 8, 17, 4, 18]
		System.out.println();

		//#6 deleteLast()
		MyLinkedList q6 = new MyLinkedList(1, 8, 19, 4, 17);
		System.out.println(q6.deleteLast()); // 17
		System.out.println(q6); // [1, 8, 19, 4]
		System.out.println();
	}
}
