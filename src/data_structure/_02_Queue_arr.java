package data_structure;

public class _02_Queue_arr {

	public class Node<E> {
		E data;
		Node<E> next;
		
		Node(E data) {
			this.data = data;
			next = null;
		}
	}
	
	public class Queue<E> {
		Node<E> front;
		Node<E> rear;
		int size;
		
		public Queue() {
			front = null;
			rear = null;
			size = 0;
		}
		
		public void offer(E el) {
			Node<E> newNode = new Node<E>(el);
			
			if(size == 0) {
				front = newNode;
			} else {
				rear.next = newNode;
			}
			rear = newNode;
			size++;
		}
		
		public E poll() {
			if(size == 0) {
				return null;
			}
			
			E el = front.data;
			front = front.next;
			size--;
			
			return el; 
		}
		
		public E peek() {
			if(size == 0) {
				return null;
			}

			return front.data; 
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public int size() {
			return size;
		}
		
		public boolean contain(E el) {
			Node<E> p = front;
			while(p != null) {
				if(el.equals(p.data)) {
					return true;
				}
				p = p.next;
			}
			return false;
		}
		
		public void clear() {
			Node<E> p = front;
			while(p != null) {
				Node<E> next = p.next;
				p.data = null;
				p.next = null;
				p = next;
			}
			size = 0;
			front = rear = null;
		}
		
		
	}
}
