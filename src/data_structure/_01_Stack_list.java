package data_structure;

public class _01_Stack_list {
	
	public class Node {
		int data;
		Node prev;
	}
	
	public class Stack {
		Node top = null;
		int size = 0;
		
		public void push(int data) {
			Node next = new Node();
			next.data = data;
			next.prev = top;
			top = next;
			
			size++;
		}
		
		public Node poll() {
			if(isEmpty()) {
				System.out.println("empty stack");
				return null;
			}
			
			Node last = new Node();
			last.data = top.data;
			top = top.prev;
			size--;
			
			return last;
		}
		
		public int peek() {
			if(isEmpty()) {
				System.out.println("empty stack");
				return 0;
			}
			
			return top.data;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public int size() {
			return size;
		}
		
	}
}
